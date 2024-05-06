package com.codigo.msvizacuti.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 150)
    private String apellido;

    @Column(name = "tipodocumento", nullable = false, length = 5)
    private String tipodocumento;

    @Column(name = "numerodocumento", nullable = false, length = 20)
    private String numerodocumento;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "usuacrea", nullable = false ,length = 45)
    private String usuaCrea;

    @Column(name = "datecreate", nullable = false)
    private Timestamp dateCreate;

    @Column(name = "usuamodif", length = 45)
    private String usuaModif;

    @Column(name = "datemodif")
    private Timestamp dateModif;

    @Column(name = "usuadelet", length = 45)
    private String usuaDelet;

    @Column(name = "datedelet")
    private Timestamp dateDelet;

    @Column(name = "empresa_id")
    private Integer empresa_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaEntity empresa;
}
