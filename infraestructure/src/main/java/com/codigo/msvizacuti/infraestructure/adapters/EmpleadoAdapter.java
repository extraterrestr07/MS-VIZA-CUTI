package com.codigo.msvizacuti.infraestructure.adapters;

import com.codigo.msvizacuti.domain.aggregates.constants.Constant;
import com.codigo.msvizacuti.domain.aggregates.dto.EmpleadoDto;
import com.codigo.msvizacuti.domain.aggregates.request.EmpleadoRequest;
import com.codigo.msvizacuti.domain.ports.out.EmpleadoServiceOut;
import com.codigo.msvizacuti.infraestructure.dao.EmpleadoRepository;
import com.codigo.msvizacuti.infraestructure.dao.PersonaRepository;
import com.codigo.msvizacuti.infraestructure.entity.EmpleadoEntity;
import com.codigo.msvizacuti.infraestructure.mapper.EmpleadoMapper;
import com.codigo.msvizacuti.infraestructure.redis.RedisService;
import com.codigo.msvizacuti.infraestructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EmpleadoAdapter implements EmpleadoServiceOut {
    private final EmpleadoRepository empleadoRepository;
    private final RedisService redisService;
    private final PersonaRepository personaRepository;

    @Override
    public EmpleadoDto crearEmpleadoOut(EmpleadoRequest empleadoRequest) {
        EmpleadoEntity empleadoEntity =  getEntity(empleadoRequest,false, null);
        return EmpleadoMapper.fromEntity(empleadoRepository.save(empleadoEntity));
    }

    @Override
    public Optional<EmpleadoDto> buscarXIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERCLIENTE+id);
        if(redisInfo!= null){
            EmpleadoDto empleadoDto = Util.convertirDesdeString(redisInfo,EmpleadoDto.class);
            return Optional.of(empleadoDto);
        }else{
            EmpleadoDto empleadoDto = EmpleadoMapper.fromEntity(empleadoRepository.findById(id).get());
            String dataForRedis = Util.convertirAString(empleadoDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENEREMPLEADO+id,dataForRedis,10);
            return Optional.of(empleadoDto);
        }
    }

    @Override
    public List<EmpleadoDto> obtenerTodosOut() {
        List<EmpleadoDto> listaDto = new ArrayList<>();
        List<EmpleadoEntity> entidades = empleadoRepository.findAll();
        for (EmpleadoEntity entidad :entidades){
            listaDto.add(EmpleadoMapper.fromEntity(entidad));
        }
        return listaDto;
    }

    @Override
    public EmpleadoDto actualizarOut(Long id, EmpleadoRequest empleadoRequest) {
        Optional<EmpleadoEntity> datoExtraido = empleadoRepository.findById(id);
        if(datoExtraido.isPresent()){
            EmpleadoEntity empleadoEntity = getEntity(empleadoRequest,true, id);
            return EmpleadoMapper.fromEntity(empleadoRepository.save(empleadoEntity));
        }else {
            throw new RuntimeException();
        }
    }

    private EmpleadoEntity getEntity(EmpleadoRequest empleadoRequest, boolean actualizar, Long id){
        //Exec servicio
        EmpleadoEntity entity = new EmpleadoEntity();
        entity.setSueldoEmpleado(empleadoRequest.getSueldoEmpleado());
        entity.setPersona(personaRepository.findById(empleadoRequest.getIdPersona()).get());
        if(actualizar){
            entity.setIdEmpleado(id);
        }
        return entity;
    }
}
