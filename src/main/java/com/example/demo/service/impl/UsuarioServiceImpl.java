package com.example.demo.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.entity.UsuarioEntity;

import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import com.example.demo.utils.Utilitarios;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	

	
	@Override
	public void crearUsuario(UsuarioEntity usuarioEntity, Model model, MultipartFile foto) {
		String nombreFoto = Utilitarios.guardarImagen(foto);
		usuarioEntity.setUrlImagen(nombreFoto);
		
		String passwordHash = Utilitarios.extraerHash(usuarioEntity.getContraUsuario());
		usuarioEntity.setContraUsuario(passwordHash);
		
	
		usuarioRepository.save(usuarioEntity);
		
		model.addAttribute("registroCorrecto", "Registro Correcto");
		model.addAttribute("usuario", new UsuarioEntity());
		
	}

	@Override
	public boolean validarUsuario(UsuarioEntity usuarioEntity, HttpSession session) {
		UsuarioEntity usuarioEncontradoPorcCorreo = 
				usuarioRepository.findByCorreoUsuario(usuarioEntity.getCorreoUsuario());
		
		if(usuarioEncontradoPorcCorreo == null) {
			return false;
		}
		if(!Utilitarios.checkPassword(usuarioEntity.getContraUsuario(), 
				usuarioEncontradoPorcCorreo.getContraUsuario())) {
			return false;
		}
		
		session.setAttribute("usuario", usuarioEncontradoPorcCorreo.getCorreoUsuario());
		
		return true;
	}

	@Override
	public UsuarioEntity buscarUsuarioPorCorreo(String correo) {
		return usuarioRepository.findByCorreoUsuario(correo);
	}

	@Override
	public UsuarioEntity actualizarUsuario(UsuarioEntity usuarioEntity) {
	    UsuarioEntity buscarUsuarioId = usuarioRepository.findByUsuarioId(usuarioEntity.getUsuarioId());
	    if (buscarUsuarioId != null) {
	    	buscarUsuarioId.setNomUsu(usuarioEntity.getNomUsu());
	    	buscarUsuarioId.setApeUsuario(usuarioEntity.getApeUsuario());
	    	buscarUsuarioId.setFonoUsuario(usuarioEntity.getFonoUsuario());
	    	
	
	        return usuarioRepository.save(buscarUsuarioId);
	    }
	    return null; 
	}

	@Override
	public UsuarioEntity buscarUsuarioId(Integer id) {
		return usuarioRepository.findByUsuarioId(id);
	}

	
	

		
	
	


}
