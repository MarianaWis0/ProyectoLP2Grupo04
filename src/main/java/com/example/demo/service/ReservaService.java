package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.ReservasEntity;
import com.example.demo.entity.UsuarioEntity;


@Service
public interface ReservaService {
	void crearReserva(ReservasEntity reservasEntity, Model model);

	List<ReservasEntity> listarReservasPorUsuario(UsuarioEntity usuario);
	void eliminarReserva(Integer id);
	
	ReservasEntity buscarReservaPorId(Integer id);
}
