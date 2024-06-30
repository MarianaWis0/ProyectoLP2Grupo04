package com.example.demo.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString	
@Table(name="tb_usuario")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Integer usuarioId;
	
	@Column(name = "nombre" ,
			nullable = false ,
			length = 100,
			columnDefinition = "VARCHAR(100)"
			)
	private String nomUsu;
	
	
	@Column(name = "apellido" ,
			nullable = false ,
			length = 100,
			columnDefinition = "VARCHAR(100)"
			)
	private String apeUsuario;
	
	
	@Column(name = "correo_electronico" ,
			nullable = false ,
			length = 100,
			columnDefinition = "VARCHAR(100)",
			unique = true
			)
	private String correoUsuario;
	
	
	@Column(name = "telefono" ,
			nullable = false ,
			length = 9,
			columnDefinition = "CHAR(9)"
			)
	private String fonoUsuario;
	
	@Column(name = "dni" ,
			nullable = false ,
			length = 8,
			columnDefinition = "CHAR(8)",
			unique = true)
	private String dniUsuario;
	
	
	@Column(name = "contrasena" ,
			nullable = false 
			)
	private String contraUsuario;
	
	private String urlImagen;
	
	

	
	
	
}