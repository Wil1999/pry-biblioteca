package com.app_biblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app_biblioteca.domain.Autor;
import com.app_biblioteca.domain.Libro;
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.exception.EntityNotFoundExceptionMessages;
import com.app_biblioteca.exception.IllegalOperationException;
import com.app_biblioteca.repository.AutorRepository;
import com.app_biblioteca.repository.LibroRepository;
import com.app_biblioteca.service.AutorService;

import jakarta.transaction.Transactional;

@Service
public class AutorServiceImpl implements AutorService{

	@Autowired
	public AutorRepository autorR;
	
	@Autowired
	public LibroRepository libroR;
	
	@Override
	@Transactional
	public List<Autor> list_all() {
		List<Autor> autores = autorR.findAll();
		if(autores.isEmpty())throw new EntityNotFoundException(EntityNotFoundExceptionMessages.AUTORES_NOT_FOUND);
		return autores;
	}
	
	@Override
	@Transactional
	public Autor findById(Long id) throws EntityNotFoundException {
		Optional<Autor> autor = autorR.findById(id);
		if(autor.isEmpty())throw new EntityNotFoundException(EntityNotFoundExceptionMessages.AUTOR_NOT_FOUND);
		return autor.get();
	}

	@Override
	@Transactional
	public Autor save(Autor autor){
		return autorR.save(autor);
	}

	@Override
	@Transactional
	public Autor update(Autor autor, Long id) throws  EntityNotFoundException {
		Optional<Autor> autorFinded =  autorR.findById(id);
		if(!autorFinded.isPresent()) {
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.AUTOR_NOT_FOUND);
		}
		autor.setIdAutor(id);
		return autorR.save(autor);
	}

	@Override
	@Transactional
	public void delete(Long id) throws EntityNotFoundException, IllegalOperationException {
		Autor autorFinded = autorR.findById(id).orElseThrow(()-> new EntityNotFoundException(EntityNotFoundExceptionMessages.AUTOR_NOT_FOUND));
		List<Libro> libros = libroR.findByAutor(autorFinded);
		if(!libros.isEmpty()) {
			throw new IllegalOperationException("El autor tiene de uno o mas libros");
		}
		if(autorFinded.getLibros() != null) {
			throw new IllegalOperationException("El autor tiene libros asignados");
		}
		autorR.deleteById(id);
	}


}
