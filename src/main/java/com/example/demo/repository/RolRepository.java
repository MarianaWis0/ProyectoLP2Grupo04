package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.RolEntity;

@Repository
public interface RolRepository extends JpaRepository < RolEntity , Integer>{

}
