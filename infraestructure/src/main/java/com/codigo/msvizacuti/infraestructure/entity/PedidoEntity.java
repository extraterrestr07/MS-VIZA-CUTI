package com.codigo.msvizacuti.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "total")
    private Double total;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity clienteEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pedido_producto",
            joinColumns = @JoinColumn(name = "fk_pedido", nullable = false),
            inverseJoinColumns = @JoinColumn(name="fk_producto", nullable = false)
    )
    private List<ProductoEntity> productos;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "usua_crea", length = 45)
    private String usuaCrea;

    @Column(name = "date_create")
    private Timestamp dateCreate;

    @Column(name = "usua_modif", length = 45)
    private String usuaModif;

    @Column(name = "date_modif")
    private Timestamp dateModif;

    @Column(name = "usua_delet", length = 45)
    private String usuaDelet;

    @Column(name = "date_delet")
    private Timestamp dateDelet;

}
