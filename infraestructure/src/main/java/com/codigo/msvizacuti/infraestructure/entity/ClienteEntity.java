package com.codigo.msvizacuti.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "saldo_cliente")
    private Double saldoCliente;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_persona", nullable = false, unique = true)
    private PersonaEntity persona;









}
