package com.codigo.msvizacuti.infraestructure.adapters;

import com.codigo.msvizacuti.domain.aggregates.constants.Constant;
import com.codigo.msvizacuti.domain.aggregates.dto.PedidoDto;
import com.codigo.msvizacuti.domain.aggregates.request.PedidoRequest;
import com.codigo.msvizacuti.domain.ports.out.PedidoServiceOut;
import com.codigo.msvizacuti.infraestructure.dao.ClienteRepository;
import com.codigo.msvizacuti.infraestructure.dao.PedidoRepository;
import com.codigo.msvizacuti.infraestructure.dao.ProductoRepository;
import com.codigo.msvizacuti.infraestructure.entity.PedidoEntity;
import com.codigo.msvizacuti.infraestructure.entity.ProductoEntity;
import com.codigo.msvizacuti.infraestructure.mapper.PedidoMapper;
import com.codigo.msvizacuti.infraestructure.redis.RedisService;
import com.codigo.msvizacuti.infraestructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoAdapter implements PedidoServiceOut {
    private final ClienteRepository clienteRepository;
    private final ProductoAdapter productoAdapter;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final RedisService redisService;

    @Override
    public PedidoDto crearPedidoOut(PedidoRequest pedidoRequest) {
        PedidoEntity pedidoEntity = getEntity2(pedidoRequest,false, null);
        return PedidoMapper.fromEntity(pedidoRepository.save(pedidoEntity));
    }

    @Override
    public Optional<PedidoDto> buscarXIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERPERSONA+id);
        if(redisInfo!= null){
            PedidoDto personaDto = Util.convertirDesdeString(redisInfo,PedidoDto.class);
            return Optional.of(personaDto);
        }else{
            PedidoDto pedidoDto = PedidoMapper.fromEntity(pedidoRepository.findById(id).get());
            String dataForRedis = Util.convertirAString(pedidoDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENERPEDIDO+id,dataForRedis,2);
            return Optional.of(pedidoDto);
        }
    }

    @Override
    public List<PedidoDto> obtenerTodosOut() {
        List<PedidoDto> listaDto = new ArrayList<>();
        List<PedidoEntity> entidades = pedidoRepository.findAll();
        for (PedidoEntity dato :entidades){
            listaDto.add(PedidoMapper.fromEntity(dato));
        }
        return listaDto;
    }

    @Override
    public PedidoDto actualizarOut(Long id, PedidoRequest pedidoRequest) {
        Optional<PedidoEntity> datoExtraido = pedidoRepository.findById(id);
        if(datoExtraido.isPresent()){
            PedidoEntity personaEntity = getEntity2(pedidoRequest,true, id);
            return PedidoMapper.fromEntity(pedidoRepository.save(personaEntity));
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public PedidoDto deleteOut(Long id) {
        Optional<PedidoEntity> datoExtraido = pedidoRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(0);
            datoExtraido.get().setUsuaDelet(Constant.USU_ADMIN);
            datoExtraido.get().setDateDelet(getTimestamp());
            return PedidoMapper.fromEntity(pedidoRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();
        }
    }

    private PedidoEntity getEntity2(PedidoRequest pedidoRequest, boolean actualiza, Long id){
        //Exec servicio
        PedidoEntity entity = new PedidoEntity();
        entity.setTotal(pedidoRequest.getTotal());
        entity.setClienteEntity(clienteRepository.findById(pedidoRequest.getIdCliente()).get());
        List<ProductoEntity> productos = pedidoRequest.getProductos().stream()
                .map(productoRequest -> productoAdapter.getEntity(productoRequest, false, null))
                .collect(Collectors.toList());
        entity.setProductos(productos);
        entity.getProductos().stream().map(productoRepository::save);
        entity.setEstado(Constant.STATUS_ACTIVE);
        //Datos de auditoria donde corresponda

        if(actualiza){
            //si Actualizo hago esto
            entity.setIdPedido(id);
            entity.setUsuaCrea(pedidoRepository.findById(id).get().getUsuaCrea());
            entity.setDateCreate(pedidoRepository.findById(id).get().getDateCreate());
            entity.setUsuaModif(Constant.USU_ADMIN);
            entity.setDateModif(getTimestamp());

        }else{
            //Sino Actualizo hago esto
            entity.setUsuaCrea(Constant.USU_ADMIN);
            entity.setDateCreate(getTimestamp());
        }
        return entity;
    }
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }
}
