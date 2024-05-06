package com.codigo.msvizacuti.infraestructure.mapper;

import com.codigo.msvizacuti.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvizacuti.infraestructure.entity.EmpresaEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class EmpresaMapper {
    public static EmpresaDto fromEntity(EmpresaEntity empresaEntity){
        EmpresaDto empresaDto = new EmpresaDto();
        empresaDto.setId(empresaEntity.getId());
        empresaDto.setTipoDoc(empresaEntity.getTipoDocumento());
        empresaDto.setNumDoc(empresaEntity.getNumeroDocumento());
        empresaDto.setRazonSocial(empresaEntity.getRazonSocial());
        empresaDto.setEstado(empresaEntity.getEstado());
        empresaDto.setCondicion(empresaEntity.getCondicion());
        empresaDto.setEsAgenteRetencion(empresaEntity.getEsAgenteRetencion());
        empresaDto.setUsuaCrea(empresaEntity.getUsuaCrea());
        empresaDto.setDateCreate(empresaEntity.getDateCreate());
        empresaDto.setUsuaModif(empresaEntity.getUsuaModif());
        empresaDto.setDateModif(empresaEntity.getDateModif());
        empresaDto.setUsuaDelet(empresaEntity.getUsuaDelet());
        empresaDto.setDateDelet(empresaEntity.getDateDelet());
        return empresaDto;
    }
}
