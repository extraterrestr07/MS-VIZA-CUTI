package com.codigo.msvizacuti.infraestructure.adapters;

import com.codigo.msvizacuti.domain.aggregates.constants.Constant;
import com.codigo.msvizacuti.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvizacuti.domain.aggregates.dto.PersonaDto;
import com.codigo.msvizacuti.domain.aggregates.dto.ReniecDto;
import com.codigo.msvizacuti.domain.aggregates.request.PersonaRequest;
import com.codigo.msvizacuti.domain.ports.out.PersonaServiceOut;
import com.codigo.msvizacuti.infraestructure.client.ClienteReniec;
import com.codigo.msvizacuti.infraestructure.dao.EmpresaRepository;
import com.codigo.msvizacuti.infraestructure.dao.PersonaRepository;
import com.codigo.msvizacuti.infraestructure.entity.EmpresaEntity;
import com.codigo.msvizacuti.infraestructure.entity.PersonaEntity;
import com.codigo.msvizacuti.infraestructure.mapper.PersonaMapper;
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
public class PersonaAdapter implements PersonaServiceOut {
    private final PersonaRepository personaRepository;
    private final EmpresaRepository empresaRepository;
    private final ClienteReniec clientReniec;
    private final RedisService redisService;

    @Value("${token.reniec}")
    private String tokenReniec;
    @Override
    public PersonaDto crearPersonaOut(PersonaRequest personaRequest) {
        PersonaEntity personaEntity = getEntity(personaRequest,false, null);
        return PersonaMapper.fromEntity(personaRepository.save(personaEntity));
    }

    @Override
    public Optional<PersonaDto> buscarXIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERPERSONA+id);
        if(redisInfo!= null){
            PersonaDto personaDto = Util.convertirDesdeString(redisInfo,PersonaDto.class);
            return Optional.of(personaDto);
        }else{
            PersonaDto personaDto = PersonaMapper.fromEntity(personaRepository.findById(id).get());
            String dataForRedis = Util.convertirAStringPersona(personaDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENERPERSONA+id,dataForRedis,2);
            return Optional.of(personaDto);
        }
    }

    @Override
    public List<PersonaDto> obtenerTodosOut() {
        List<PersonaDto> listaDto = new ArrayList<>();
        List<PersonaEntity> entidades = personaRepository.findAll();
        for (PersonaEntity dato :entidades){
            listaDto.add(PersonaMapper.fromEntity(dato));
        }
        return listaDto;
    }

    @Override
    public PersonaDto actualizarOut(Long id, PersonaRequest personaRequest) {
        Optional<PersonaEntity> datoExtraido = personaRepository.findById(id);
        if(datoExtraido.isPresent()){
            PersonaEntity personaEntity = getEntity(personaRequest,true, id);
            return PersonaMapper.fromEntity(personaRepository.save(personaEntity));
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public PersonaDto deleteOut(Long id) {
        return null;
    }

    private PersonaEntity getEntity(PersonaRequest personaRequest, boolean actualiza, Long id){
        //Exec servicio
        ReniecDto reniecDto = getExecReniec(personaRequest.getNumDoc());
        PersonaEntity entity = new PersonaEntity();
        entity.setNumerodocumento(reniecDto.getNumeroDocumento());
        entity.setTipodocumento(reniecDto.getTipoDocumento());
        entity.setEmail(reniecDto.getNombres()+"@gmail.com");
        entity.setEstado(Constant.STATUS_ACTIVE);
        entity.setEmpresa(getEmpresa());
        //Datos de auditoria donde corresponda

        if(actualiza){
            //si Actualizo hago esto
            entity.setId(id);
            entity.setUsuaModif(Constant.USU_ADMIN);
            entity.setDateModif(getTimestamp());

        }else{
            //Sino Actualizo hago esto
            entity.setUsuaCrea(Constant.USU_ADMIN);
            entity.setDateCreate(getTimestamp());
        }

        return entity;
    }
    private EmpresaEntity getEmpresa(Long id){
        Optional<EmpresaEntity> empresa= empresaRepository.findById(id);
        if(empresa.isPresent())
            return empresa.get();
        else
            return null;
    }
    private ReniecDto getExecReniec(String numDoc){
        String authorization = "Bearer "+tokenReniec;
        return clientReniec.getInfoReniec(numDoc,authorization);
    }
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }
}
