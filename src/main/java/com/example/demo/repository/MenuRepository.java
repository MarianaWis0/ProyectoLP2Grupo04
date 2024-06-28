package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {
    List<MenuEntity> findByCategoriaCatId(Integer catId); 
    
}
