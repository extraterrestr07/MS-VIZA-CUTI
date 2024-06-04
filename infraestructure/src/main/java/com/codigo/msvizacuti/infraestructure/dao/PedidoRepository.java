package com.codigo.msvizacuti.infraestructure.dao;

import com.codigo.msvizacuti.infraestructure.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
