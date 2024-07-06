package com.example.demo.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_reservas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservasEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reserva")
		private Integer reservaId;
	
	@Column(name = "fecha_reserva", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	    private Date fecReserva;
	
	@Column(name = "numero_personas", nullable = false)
	private int numPersonas;
	

	@Column(name = "ocacion_especial", nullable = true,  length = 100)
	private String ocacion;
	

	
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable = false)
	private UsuarioEntity usuarios;
}