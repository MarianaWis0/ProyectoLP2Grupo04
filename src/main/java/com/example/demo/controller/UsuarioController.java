package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.entity.UsuarioEntity;

import com.example.demo.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	

	
	@GetMapping("/registrar")
	public String showRegistrarUsuario(Model model) {
		model.addAttribute("usuario", new UsuarioEntity());
		return "registrarUsuario";
	}
	
	@PostMapping("/registrar")
	public String registrarUsuario(UsuarioEntity usuarioEntity, Model model, 
			@RequestParam("foto")MultipartFile foto) {
		
		usuarioService.crearUsuario(usuarioEntity, model, foto);
		
		return "registrarUsuario";
	}
	
	
	@GetMapping("/login")
	public String index(Model model) {
	    model.addAttribute("usuario", new UsuarioEntity());
	    return "login";
	}

	@PostMapping("/login")
	public String login(UsuarioEntity usuarioEntity, Model model, HttpSession session) {
	    boolean usuarioValido = usuarioService.validarUsuario(usuarioEntity, session);
	    if (usuarioValido) {
	        return "redirect:/categoria";
	    }
	    model.addAttribute("loginInvalido", "No existe el usuario");
		model.addAttribute("usuario", new UsuarioEntity());
	    return "login";
	}


	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/editar_usuario")
	public String showEditarUsuario(Model model, HttpSession session) {
	    if (session.getAttribute("usuario") == null) {
	        return "redirect:/login";
	    }

	    String correo = session.getAttribute("usuario").toString();
	    UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
	    model.addAttribute("usuario", usuarioEntity); 
	    model.addAttribute("foto", usuarioEntity.getUrlImagen());

	    return "editarUsuario";
	}

	@PostMapping("/editar_usuario")
	public String actualizarUsuario(@ModelAttribute("usuario") UsuarioEntity usuarioEntity, HttpSession session) {
	    if (session.getAttribute("usuario") == null) {
	        return "redirect:/login";
	    }

	    UsuarioEntity usuarioActualizado = usuarioService.actualizarUsuario(usuarioEntity);
	 

	    session.setAttribute("usuario", usuarioActualizado.getCorreoUsuario());

	    return "redirect:/categoria";
	}
	
	
    
  


	
	
}
