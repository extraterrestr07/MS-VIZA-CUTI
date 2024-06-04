package com.codigo.msvizacuti.infraestructure.adapters;

import com.codigo.msvizacuti.domain.aggregates.constants.Constant;
import com.codigo.msvizacuti.domain.aggregates.dto.ClienteDto;
import com.codigo.msvizacuti.domain.aggregates.request.ClienteRequest;
import com.codigo.msvizacuti.domain.ports.out.ClienteServiceOut;
import com.codigo.msvizacuti.infraestructure.dao.ClienteRepository;
import com.codigo.msvizacuti.infraestructure.dao.PersonaRepository;
import com.codigo.msvizacuti.infraestructure.entity.ClienteEntity;
import com.codigo.msvizacuti.infraestructure.mapper.ClienteMapper;
import com.codigo.msvizacuti.infraestructure.redis.RedisService;
import com.codigo.msvizacuti.infraestructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ClienteAdapter implements ClienteServiceOut {
    private final ClienteRepository clienteRepository;
    private final RedisService redisService;
    private final PersonaRepository personaRepository;

    @Override
    public ClienteDto crearClienteOut(ClienteRequest clienteRequest) {
        ClienteEntity clienteEntity =  getEntity(clienteRequest,false, null);
        return ClienteMapper.fromEntity(clienteRepository.save(clienteEntity));
    }

    @Override
    public Optional<ClienteDto> buscarXIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERCLIENTE+id);
        if(redisInfo!= null){
            ClienteDto clienteDto = Util.convertirDesdeString(redisInfo,ClienteDto.class);
            return Optional.of(clienteDto);
        }else{
            ClienteDto clienteDto = ClienteMapper.fromEntity(clienteRepository.findById(id).get());
            String dataForRedis = Util.convertirAString(clienteDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENERCLIENTE+id,dataForRedis,10);
            return Optional.of(clienteDto);
        }
    }

    @Override
    public List<ClienteDto> obtenerTodosOut() {
        List<ClienteDto> listaDto = new ArrayList<>();
        List<ClienteEntity> entidades = clienteRepository.findAll();
        for (ClienteEntity entidad :entidades){
            listaDto.add(ClienteMapper.fromEntity(entidad));
        }
        return listaDto;
    }

    @Override
    public ClienteDto actualizarOut(Long id, ClienteRequest clienteRequest) {
        Optional<ClienteEntity> datoExtraido = clienteRepository.findById(id);
        if(datoExtraido.isPresent()){
            ClienteEntity clienteEntity = getEntity(clienteRequest,true, id);
            return ClienteMapper.fromEntity(clienteRepository.save(clienteEntity));
        }else {
            throw new RuntimeException();
        }
    }

    private ClienteEntity getEntity(ClienteRequest clienteRequest, boolean actualizar, Long id){
        //Exec servicio
        ClienteEntity entity = new ClienteEntity();
        entity.setSaldoCliente(clienteRequest.getSaldoCliente());
        entity.setPersona(personaRepository.findById(clienteRequest.getIdPersona()).get());
        if(actualizar){
            entity.setIdCliente(id);
        }
        return entity;
    }
}
