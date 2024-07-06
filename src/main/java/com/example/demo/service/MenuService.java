package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.MenuEntity;


@Service
public interface MenuService {
    
	 List<MenuEntity> obtenerMenu();
	    List<MenuEntity> buscarMenuPorCategoria(Integer catId);
	    List<CategoriaEntity> obtenerCategorias();
	    MenuEntity buscarMenuPorId(Integer id);
	     
}

