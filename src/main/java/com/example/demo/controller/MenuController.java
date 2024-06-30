package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.MenuEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.service.MenuService;
import com.example.demo.service.UsuarioService;

@Controller

public class MenuController {

    @Autowired
    private MenuService menuService;

	@Autowired
	private UsuarioService usuarioService;
    
    @GetMapping("/categoria")
	public String showMenu(HttpSession session, Model model , @RequestParam(name = "catId", required = false) Integer catId) {
		if(session.getAttribute("usuario") == null) {
			return "redirect:/login";
		}
		
		String correo = session.getAttribute("usuario").toString();
		UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
		model.addAttribute("foto", usuarioEntity.getUrlImagen());
		
		List<MenuEntity> menu;
        if (catId != null) {
            menu = menuService.buscarMenuPorCategoria(catId);
        } else {
            menu = menuService.obtenerMenu();
        }
        List<CategoriaEntity> categorias = menuService.obtenerCategorias();
        model.addAttribute("menu", menu);
        model.addAttribute("categorias", categorias);
	
											
		return "menu";
	}

  


}
