package com.app_biblioteca.service;

import java.util.List;

import com.app_biblioteca.domain.Autor;
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.exception.IllegalOperationException;

public interface AutorService {
	
	List<Autor> list_all();
	Autor findById(Long id) throws EntityNotFoundException;
	Autor save(Autor autor);
	Autor update(Autor autor, Long id) throws EntityNotFoundException;
	void delete(Long id)throws EntityNotFoundException, IllegalOperationException;

}
