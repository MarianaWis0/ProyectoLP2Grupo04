package com.example.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "detalle_pedido")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DetallePedidoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer detalleId;
	
    @Column(name="cantidad" , nullable = false)
	private Integer cantidad;
    
    @ManyToOne
	@JoinColumn(name = "id_menu", nullable = false)
	private MenuEntity menuEntity;
    
    @ManyToOne
	@JoinColumn(name = "id_pedido", nullable = false)
	private PedidoEntity pedidoEntity;
    

    
    
}
