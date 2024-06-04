package com.codigo.msvizacuti.infraestructure.adapters;

import com.codigo.msvizacuti.domain.aggregates.constants.Constant;
import com.codigo.msvizacuti.domain.aggregates.dto.ProductoDto;
import com.codigo.msvizacuti.domain.aggregates.request.ProductoRequest;
import com.codigo.msvizacuti.domain.ports.out.ProductoServiceOut;
import com.codigo.msvizacuti.infraestructure.dao.ProductoRepository;
import com.codigo.msvizacuti.infraestructure.entity.ProductoEntity;
import com.codigo.msvizacuti.infraestructure.mapper.ProductoMapper;
import com.codigo.msvizacuti.infraestructure.redis.RedisService;
import com.codigo.msvizacuti.infraestructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoAdapter implements ProductoServiceOut {
    private final ProductoRepository productoRepository;
    private final RedisService redisService;
    @Override
    public ProductoDto crearProductoOut(ProductoRequest productoRequest) {
        ProductoEntity productoEntity = getEntity(productoRequest,false, null);
        return ProductoMapper.fromEntity(productoRepository.save(productoEntity));
    }

    @Override
    public Optional<ProductoDto> buscarXIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERPRODUCTO+id);
        if(redisInfo!= null){
            ProductoDto productoDto = Util.convertirDesdeString(redisInfo,ProductoDto.class);
            return Optional.of(productoDto);
        }else{
            ProductoDto productoDto = ProductoMapper.fromEntity(productoRepository.findById(id).get());
            String dataForRedis = Util.convertirAString(productoDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENERPRODUCTO+id,dataForRedis,2);
            return Optional.of(productoDto);
        }
    }

    @Override
    public List<ProductoDto> obtenerTodosOut() {
        List<ProductoDto> listaDto = new ArrayList<>();
        List<ProductoEntity> entidades = productoRepository.findAll();
        for (ProductoEntity entidad :entidades){
            listaDto.add(ProductoMapper.fromEntity(entidad));
        }
        return listaDto;
    }

    @Override
    public ProductoDto actualizarOut(Long id, ProductoRequest productoRequest) {
        Optional<ProductoEntity> datoExtraido = productoRepository.findById(id);
        if(datoExtraido.isPresent()){
            ProductoEntity productoEntity = getEntity(productoRequest,true, id);
            productoEntity.setDateCreate(datoExtraido.get().getDateCreate());
            productoEntity.setUsuaCrea(datoExtraido.get().getUsuaCrea());
            return ProductoMapper.fromEntity(productoRepository.save(productoEntity));
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public ProductoDto deleteOut(Long id) {
        Optional<ProductoEntity> datoExtraido = productoRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(0);
            datoExtraido.get().setUsuaDelet(Constant.USU_ADMIN);
            datoExtraido.get().setDateDelet(getTimestamp());
            return ProductoMapper.fromEntity(productoRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();
        }
    }

    protected ProductoEntity getEntity(ProductoRequest productoRequest, boolean actualiza, Long id){

        ProductoEntity entity = new ProductoEntity();
        entity.setNombre(productoRequest.getNombre());
        entity.setPrecioUnidad(productoRequest.getPrecioUnidad());
        entity.setEstado(Constant.STATUS_ACTIVE);
        //Datos de auditoria donde corresponda

        if(actualiza){
            //si Actualizo hago esto
            entity.setIdProducto(id);
            entity.setUsuaCrea(productoRepository.findById(id).get().getUsuaCrea());
            entity.setDateCreate(productoRepository.findById(id).get().getDateCreate());
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
