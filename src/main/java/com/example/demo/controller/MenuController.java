package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.DetallePedidoEntity;
import com.example.demo.entity.MenuEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.model.Pedido;
import com.example.demo.service.MenuService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.impl.PdfService;
import com.itextpdf.io.exceptions.IOException;

@Controller

public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
	private PdfService pdfService;

	@Autowired
	private UsuarioService usuarioService;
    
	  @GetMapping("/categoria")
		public String showMenu(HttpSession session, Model model , @RequestParam(name = "catId", required = false) Integer catId) {
			if(session.getAttribute("usuario") == null) {
				return "redirect:/login";
			}
			
			   String correo = session.getAttribute("usuario").toString();
			     UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
			     String nombreCompleto = usuarioEntity.getNomUsu() + " " + usuarioEntity.getApeUsuario();

			     model.addAttribute("foto", usuarioEntity.getUrlImagen());
			     model.addAttribute("nombreUsuario", nombreCompleto);
			
			List<MenuEntity> menu;
	        if (catId != null) {
	            menu = menuService.buscarMenuPorCategoria(catId);
	        } else {
	            menu = menuService.obtenerMenu();
	        }
	        List<CategoriaEntity> categorias = menuService.obtenerCategorias();
	        model.addAttribute("menu", menu);
	        model.addAttribute("categorias", categorias);
	        
	        
	        //////////////////////////////////////////////////////////////////////
			List<Pedido>menuSession = null;
			if(session.getAttribute("carrito") == null) {
				menuSession = new ArrayList<Pedido>();
			}else {
				menuSession = (List<Pedido>) session.getAttribute("carrito");
			}
			model.addAttribute("cant_carrito", menuSession.size());
			
			List<DetallePedidoEntity> detallePedidoEntityList = new ArrayList<>();
			BigDecimal total = BigDecimal.ZERO; 
			for (Pedido pedido : menuSession) {
			    DetallePedidoEntity detallePedidoEntity = new DetallePedidoEntity();
			    MenuEntity menuEntity = menuService.buscarMenuPorId(pedido.getMenuId());
			    detallePedidoEntity.setMenuEntity(menuEntity);
			    detallePedidoEntity.setCantidad(pedido.getCantidad());
			    detallePedidoEntityList.add(detallePedidoEntity);
			    BigDecimal cantidad = new BigDecimal(pedido.getCantidad());
			    BigDecimal subtotal = cantidad.multiply(menuEntity.getPrecio());
			    total = total.add(subtotal);
			}
			model.addAttribute("carrito", detallePedidoEntityList);
			model.addAttribute("total", total);
		
												
			return "menu";
		}

	  


	  @PostMapping("/agregar_producto")
	  public String agregarProducto(HttpSession session,
	          @RequestParam("menuId") String menuId,
	          @RequestParam("cant") Integer cantidad) {
	      
	      List<Pedido> carrito = session.getAttribute("carrito") == null ? new ArrayList<>() : (List<Pedido>) session.getAttribute("carrito");
	      
	      Pedido pedido = new Pedido(cantidad, Integer.parseInt(menuId));
	      carrito.add(pedido);
	      
	      session.setAttribute("carrito", carrito);
	      
	      return "redirect:/categoria"; 
	  }

	
	
	
	
	  @GetMapping("/generar_pdf")
	  public ResponseEntity<InputStreamResource> generarPdf(HttpSession session, Model model) throws IOException, java.io.IOException {
	      String correo = session.getAttribute("usuario").toString();
	      UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
	      String nombreCompleto = usuarioEntity.getNomUsu() + " " + usuarioEntity.getApeUsuario();

	      model.addAttribute("nombreUsuario", nombreCompleto);
	      
	      List<Pedido> menuSession = session.getAttribute("carrito") == null ? new ArrayList<>() : (List<Pedido>) session.getAttribute("carrito");

	      List<DetallePedidoEntity> detallePedidoEntityList = new ArrayList<>();
	      BigDecimal total = BigDecimal.ZERO;

	      for (Pedido pedido : menuSession) {
	          DetallePedidoEntity detalle = new DetallePedidoEntity();
	          MenuEntity menuEntity = menuService.buscarMenuPorId(pedido.getMenuId()); // Obtener el MenuEntity por el menuId
	          detalle.setMenuEntity(menuEntity);
	          detalle.setCantidad(pedido.getCantidad());
	          detallePedidoEntityList.add(detalle);
	          total = total.add(menuEntity.getPrecio().multiply(new BigDecimal(pedido.getCantidad())));
	      }

	      Map<String, Object> datosPdf = new HashMap<>();
	      datosPdf.put("factura", detallePedidoEntityList);
	      datosPdf.put("precio_total", total);
	      datosPdf.put("nombreUsuario", nombreCompleto);

	      ByteArrayInputStream pdfBytes = pdfService.generarPdfDeHtml("pdf", datosPdf);

	      HttpHeaders httpHeaders = new HttpHeaders();
	      httpHeaders.add("Content-Disposition", "inline; filename=Pedido.pdf");

	      return ResponseEntity.ok()
	              .headers(httpHeaders)
	              .contentType(MediaType.APPLICATION_PDF)
	              .body(new InputStreamResource(pdfBytes));
	  }



  


}
