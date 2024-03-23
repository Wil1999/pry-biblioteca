package com.app_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app_biblioteca.domain.Libro;

public interface LibroRepository extends JpaRepository<Libro,Long>{

}
