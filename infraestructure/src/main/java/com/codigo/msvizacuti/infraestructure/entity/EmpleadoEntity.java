package com.codigo.msvizacuti.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empleado")
@Getter
@Setter
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @Column(name = "sueldo_empleado")
    private Double sueldoEmpleado;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntity persona;

    }