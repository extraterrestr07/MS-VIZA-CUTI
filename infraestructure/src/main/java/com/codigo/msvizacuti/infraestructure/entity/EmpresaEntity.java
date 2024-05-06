package com.codigo.msvizacuti.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "empresa_info")
@Getter
@Setter
public class EmpresaEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "razonsocial", nullable = false, length = 150)
    private String razonSocial;

    @Column(name = "tipodocumento", nullable = false, length = 15)
    private String tipoDocumento;

    @Column(name = "numerodocumento", nullable = false, length = 15)
    private String numeroDocumento;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "condicion", nullable = false)
    private String condicion;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "esagenteretencion", nullable = false)
    private Boolean EsAgenteRetencion;

    @Column(name = "usuacrea", length = 45)
    private String usuaCrea;

    @Column(name = "datecreate")
    private Timestamp dateCreate;

    @Column(name = "usuamodif", length = 45)
    private String usuaModif;

    @Column(name = "datemodif")
    private Timestamp dateModif;

    @Column(name = "usuadelet", length = 45)
    private String usuaDelet;

    @Column(name = "datedelet")
    private Timestamp dateDelet;
}
