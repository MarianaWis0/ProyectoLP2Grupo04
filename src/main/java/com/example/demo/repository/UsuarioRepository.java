package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository < UsuarioEntity , Integer> {
	
	UsuarioEntity findByCorreoUsuario(String correoUsuario);
	UsuarioEntity findByUsuarioId(Integer usuarioId);
	
}
