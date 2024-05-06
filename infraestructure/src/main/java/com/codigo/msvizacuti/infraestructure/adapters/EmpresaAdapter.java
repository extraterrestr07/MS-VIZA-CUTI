package com.codigo.msvizacuti.infraestructure.adapters;

import com.codigo.msvizacuti.domain.aggregates.constants.Constant;
import com.codigo.msvizacuti.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvizacuti.domain.aggregates.dto.SunatDto;
import com.codigo.msvizacuti.domain.aggregates.request.EmpresaRequest;
import com.codigo.msvizacuti.domain.ports.out.EmpresaServiceOut;
import com.codigo.msvizacuti.infraestructure.client.ClienteSunat;
import com.codigo.msvizacuti.infraestructure.dao.EmpresaRepository;
import com.codigo.msvizacuti.infraestructure.entity.EmpresaEntity;
import com.codigo.msvizacuti.infraestructure.mapper.EmpresaMapper;
import com.codigo.msvizacuti.infraestructure.redis.RedisService;
import com.codigo.msvizacuti.infraestructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaAdapter implements EmpresaServiceOut {
    private final EmpresaRepository empresaRepository;
    private final ClienteSunat clienteSunat;
    private final RedisService redisService;

    @Value("${token.sunat}")
    private String tokenSunat;
    @Override
    public EmpresaDto crearEmpresaOut(EmpresaRequest empresaRequest) {
        EmpresaEntity empresaEntity =  getEntity(empresaRequest,false, null);
        return EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
    }

    @Override
    public Optional<EmpresaDto> buscarxIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENEREMPRESA+id);
        if(redisInfo!= null){
            EmpresaDto empresaDto = Util.convertirDesdeString(redisInfo,EmpresaDto.class);
            return Optional.of(empresaDto);
        }else{
            EmpresaDto empresaDto = EmpresaMapper.fromEntity(empresaRepository.findById(id).get());
            String dataForRedis = Util.convertirAString(empresaDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENEREMPRESA+id,dataForRedis,10);
            return Optional.of(empresaDto);
        }
    }

    @Override
    public List<EmpresaDto> obtenerTodosOut() {
        List<EmpresaDto> listaDto = new ArrayList<>();
        List<EmpresaEntity> entidades = empresaRepository.findAll();
        for (EmpresaEntity entidad :entidades){
            listaDto.add(EmpresaMapper.fromEntity(entidad));
        }
        return listaDto;
    }

    @Override
    public EmpresaDto actualizarEmpresaOut(Long id, EmpresaRequest empresaRequest) {
        Optional<EmpresaEntity> datoExtraido = empresaRepository.findById(id);
        if(datoExtraido.isPresent()){
            EmpresaEntity empresaEntity = getEntity(empresaRequest,true, id);
            empresaEntity.setDateCreate(datoExtraido.get().getDateCreate());
            empresaEntity.setUsuaCrea(datoExtraido.get().getUsuaCrea());
            return EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public EmpresaDto deleteOut(Long id) {
        Optional<EmpresaEntity> datoExtraido = empresaRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(0);
            datoExtraido.get().setUsuaDelet(Constant.USU_ADMIN);
            datoExtraido.get().setDateDelet(getTimestamp());
            return EmpresaMapper.fromEntity(empresaRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();
        }
    }

    private EmpresaEntity getEntity(EmpresaRequest empresaRequest, boolean actualizar, Long id){
        //Exec servicio
        SunatDto sunatDto = getExecutionSunat(empresaRequest.getNumDoc());
        EmpresaEntity entity = new EmpresaEntity();
        entity.setRazonSocial(sunatDto.getRazonSocial());
        entity.setTipoDocumento(sunatDto.getTipoDocumento());
        entity.setNumeroDocumento(sunatDto.getNumeroDocumento());
        entity.setCondicion(sunatDto.getCondicion());
        entity.setEstado(Constant.STATUS_ACTIVE);
        entity.setEsAgenteRetencion(false);

        if(actualizar){
            entity.setId(id);
            entity.setUsuaModif(Constant.USU_ADMIN);
            entity.setDateModif(getTimestamp());

        }else{
            entity.setUsuaCrea(Constant.USU_ADMIN);
            entity.setDateCreate(getTimestamp());
        }
        return entity;
    }

    private Timestamp getTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }

    private SunatDto getExecutionSunat(String numDoc){
        String authorization = "Bearer "+tokenSunat;
        return clienteSunat.getInfoSunat(numDoc,authorization);
    }
}
