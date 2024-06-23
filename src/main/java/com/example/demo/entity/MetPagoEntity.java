package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "metodo_pago")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MetPagoEntity {
	
	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "pago_id")
	    private Integer pagoId;
	
	@Column(name="desc_pago" , nullable = false)
	private String descPago;
}