package com.example.demo.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.MenuEntity;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.MenuRepository;
import com.example.demo.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	  @Autowired
	    private MenuRepository menuRepository;


	  @Autowired
	    private CategoriaRepository categoriaRepository;
	    
	  @Override
	    public List<MenuEntity> obtenerMenu() {
	        return menuRepository.findAll();
	    }

	  @Override
	    public List<MenuEntity> buscarMenuPorCategoria(Integer catId) {
	        return menuRepository.findByCategoriaCatId(catId);
	    }

	  @Override
	    public List<CategoriaEntity> obtenerCategorias() {
	        return categoriaRepository.findAll();
	    
	    }

	  @Override
	  public MenuEntity buscarMenuPorId(Integer id) {
	      return menuRepository.findById((int) id.longValue()).get();
	  }
   

}
