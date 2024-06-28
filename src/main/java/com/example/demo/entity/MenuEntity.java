package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_menu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_menu")
		private Integer menuId;
	
	@Column(name = "nombre_plato", nullable = false, length = 100)
	    private String nomPlato;
	
	@Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
	private String descripcion;
	
	@Column(name = "precio", nullable = false, precision = 10, scale = 2)
	private BigDecimal precio;
	
	@ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false) 
    private CategoriaEntity categoria;

	@OneToMany(mappedBy = "menuEntity", cascade = CascadeType.ALL)
	private List<DetallePedidoEntity>detallePedido;
	
	
	
}