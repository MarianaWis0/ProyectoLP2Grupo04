package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.ReservasEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService{
	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public void crearReserva(ReservasEntity reservasEntity, Model model) {
	        reservaRepository.save(reservasEntity);
	        
	        model.addAttribute("reserva", new ReservasEntity());
	        model.addAttribute("registroCorrecto", "Reserva agregado");
		
	}
	
	 @Override
	    public List<ReservasEntity> listarReservasPorUsuario(UsuarioEntity usuario) {
	        return reservaRepository.findByUsuarios(usuario);
	    }

	@Override
	public void eliminarReserva(Integer id) {
		reservaRepository.deleteById(id);
		
	}

	@Override
	public ReservasEntity buscarReservaPorId(Integer id) {
		return reservaRepository.findById(id.intValue()).get();
	}
    
	 
}
