package com.app_biblioteca.service;

import java.util.List;

import com.app_biblioteca.domain.Libro;
import com.app_biblioteca.domain.dto.LibroDTO;

public interface LibroService {
	List<Libro> list_all();
	Libro save(LibroDTO libro);
	Libro update(LibroDTO libro,Long id);
	void delete(Long id);
}
