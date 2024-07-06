package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.DetallePedidoEntity;
import com.example.demo.entity.MenuEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.model.Pedido;
import com.example.demo.service.MenuService;

@Controller
public class HomeController {

	@Autowired
	private MenuService menuService;
	
	
	@GetMapping("/nosotros")
	private String verNosotros() {
		return "home";
	}
	
	 @GetMapping("/menu")
		public String showMenuHome( Model model , @RequestParam(name = "catId", required = false) Integer catId) {
		
			List<MenuEntity> menu;
	        if (catId != null) {
	            menu = menuService.buscarMenuPorCategoria(catId);
	        } else {
	            menu = menuService.obtenerMenu();
	        }
	        List<CategoriaEntity> categorias = menuService.obtenerCategorias();
	        model.addAttribute("menu", menu);
	        model.addAttribute("categorias", categorias);
	        							
			return "homeMenu";
		}
}
