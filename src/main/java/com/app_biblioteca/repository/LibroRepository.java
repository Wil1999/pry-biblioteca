package com.app_biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app_biblioteca.domain.Autor;
import com.app_biblioteca.domain.Libro;

public interface LibroRepository extends JpaRepository<Libro,Long>{
	
	List<Libro> findByAutor(Autor autor);
}
