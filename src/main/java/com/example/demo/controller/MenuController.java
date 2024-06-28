package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.MenuEntity;
import com.example.demo.service.MenuService;

@Controller

public class MenuController {

    @Autowired
    private MenuService menuService;

    

    @GetMapping("/categoria")
    public String buscarMenuPorCategoria(@RequestParam(name = "catId", required = false) Integer catId, Model model) {
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