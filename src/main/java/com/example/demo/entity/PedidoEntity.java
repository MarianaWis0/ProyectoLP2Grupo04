package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_pedido")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer pedidoId;

    @Column(name = "fecha_pedido", nullable = false, updatable = false)
    private LocalDate  fechaPedido;

    @Column(name = "estado_pedido")
    @Enumerated(EnumType.STRING) 
    private EstadoPedido estado;

    public enum EstadoPedido {
        EN_PROCESO,
        ENTREGADO
    }
    
    @Column(name="direccion" , nullable = false)
	private String direccion;
    
    
    @ManyToOne
    @JoinColumn(name="pago_id", nullable = false)
    private MetPagoEntity pagos;

    
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable = false)
	private UsuarioEntity usuarios;
	
	@OneToMany(mappedBy = "pedidoEntity", cascade = CascadeType.ALL)
	private List<DetallePedidoEntity>detallePedido;
	
 
}