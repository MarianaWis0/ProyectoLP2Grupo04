package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.DetallePedidoEntity;
import com.example.demo.entity.MenuEntity;
import com.example.demo.entity.PedidoEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.service.MenuService;
import com.example.demo.service.UsuarioService;

@Controller
public class PedidoController {

    @Autowired
    private MenuService menuService;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping("/guardar_factura")
	public String guardarFactura(HttpSession session) {
	    String correoString = (String) session.getAttribute("usuario");
	    UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correoString);
	    
	    PedidoEntity pedidoEntity = new PedidoEntity();
	    pedidoEntity.setFechaPedido(LocalDate.now());
	    pedidoEntity.setUsuarios(usuarioEntity);
	    
	    List<DetallePedidoEntity> detallePedidoEntityList = new ArrayList<>();
	    Double total = 0.0;
	    
	    List<Pedido> menuSession = (List<Pedido>) session.getAttribute("carrito");
	    if (menuSession == null) {
	    	menuSession = new ArrayList<>();
	    }
	    
	    for (Pedido pedido : menuSession) {
	        DetallePedidoEntity detallePedidoEntity = new DetallePedidoEntity();
	        MenuEntity menuEntity = menuService.buscarMenuPorId(pedido.getMenuId());
	        
	        detallePedidoEntity.setMenuEntity(menuEntity);
	        detallePedidoEntity.setCantidad(pedido.getCantidad());
	        detallePedidoEntity.setPedidoEntity(pedidoEntity);
	        detallePedidoEntityList.add(detallePedidoEntity);
	    }
	    
	    pedidoEntity.setDetallePedido(detallePedidoEntityList);
	    pedidoRepository.save(pedidoEntity);
	    
	    session.removeAttribute("carrito");
	    
	    return "redirect:/categoria";
	}
	
}
