package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.ReservasEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.service.ReservaService;
import com.example.demo.service.UsuarioService;

@Controller
public class ReservaController {
	@Autowired
	private ReservaService reservaService;
	 @Autowired
	    private UsuarioService usuarioService;
	
	
	   @GetMapping("/reservas")
	    public String crearReserva(HttpSession session, Model model) {
	        if (session.getAttribute("usuario") == null) {
	            return "redirect:/login";
	        }

	        String correo = session.getAttribute("usuario").toString();
	        UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
	        String nombreCompleto = usuarioEntity.getNomUsu() + " " + usuarioEntity.getApeUsuario();

	        model.addAttribute("foto", usuarioEntity.getUrlImagen());
	        model.addAttribute("nombreUsuario", nombreCompleto);
	        model.addAttribute("reserva", new ReservasEntity());
	        
	       
	        model.addAttribute("reservas", reservaService.listarReservasPorUsuario(usuarioEntity));

	        return "reservasCrear";
	    }

	    @PostMapping("/reservas")
	    public String crearReserva(@ModelAttribute ReservasEntity reservasEntity, Model model, HttpSession session) {
	        if (session.getAttribute("usuario") == null) {
	            return "redirect:/login";
	        }

	        String correo = session.getAttribute("usuario").toString();
	        UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
	        String nombreCompleto = usuarioEntity.getNomUsu() + " " + usuarioEntity.getApeUsuario();

	        model.addAttribute("foto", usuarioEntity.getUrlImagen());
	        model.addAttribute("nombreUsuario", nombreCompleto);

	        reservasEntity.setUsuarios(usuarioEntity);
	        reservaService.crearReserva(reservasEntity, model);

	      
	        model.addAttribute("reservas", reservaService.listarReservasPorUsuario(usuarioEntity));

	        return "reservasCrear";
	    }
	    
	    
	    
	    
	    @GetMapping("/detalle/{id}")
		public String verProducto(HttpSession session,  Model model, @PathVariable("id") Integer id) {
			
	    	if (session.getAttribute("usuario") == null) {
	            return "redirect:/login";
	        }

	        String correo = session.getAttribute("usuario").toString();
	        UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
	        String nombreCompleto = usuarioEntity.getNomUsu() + " " + usuarioEntity.getApeUsuario();

	        model.addAttribute("foto", usuarioEntity.getUrlImagen());
	        model.addAttribute("nombreUsuario", nombreCompleto);

			
		    ReservasEntity reserva = reservaService.buscarReservaPorId(id);
		    model.addAttribute("reserva", reserva);
		    return "detalle"; 
		}
	    
	    
	    
	    
	    
	    @GetMapping("/eliminar/{id}")
	    public String eliminarProducto(@PathVariable("id") Integer id, ReservasEntity reservasEntity) {
	    	reservaService.eliminarReserva(id);
	        return "redirect:/reservas";
	    }
}
