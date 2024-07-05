package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ReservasEntity;
import com.example.demo.entity.UsuarioEntity;

@Repository
public interface ReservaRepository  extends JpaRepository < ReservasEntity , Integer>{

	List<ReservasEntity> findByUsuarios(UsuarioEntity usuario);
	
}
