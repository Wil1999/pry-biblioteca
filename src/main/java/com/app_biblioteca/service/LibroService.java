package com.app_biblioteca.service;

import java.util.List;

import com.app_biblioteca.domain.Autor;
import com.app_biblioteca.domain.Libro;
import com.app_biblioteca.domain.dto.LibroDTO;
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.exception.IllegalOperationException;

public interface LibroService {
	List<Libro> list_all();
	Libro findById(Long id)throws EntityNotFoundException;
	Libro save(Libro libro)throws IllegalOperationException;
	Libro update(Libro libro,Long id) throws EntityNotFoundException, IllegalOperationException;
	void delete(Long id)throws EntityNotFoundException, IllegalOperationException;
	
	Libro assignAuthor(Long libroId, Long autorId) throws EntityNotFoundException;
}
