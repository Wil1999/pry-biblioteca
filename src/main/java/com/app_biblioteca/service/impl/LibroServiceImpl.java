package com.app_biblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app_biblioteca.domain.Autor;
import com.app_biblioteca.domain.Libro;
import com.app_biblioteca.domain.Prestamo;
import com.app_biblioteca.domain.Usuario;
import com.app_biblioteca.domain.dto.LibroDTO;
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.exception.EntityNotFoundExceptionMessages;
import com.app_biblioteca.exception.IllegalOperationException;
import com.app_biblioteca.repository.AutorRepository;
import com.app_biblioteca.repository.LibroRepository;
import com.app_biblioteca.service.LibroService;

import jakarta.transaction.Transactional;

@Service
public class LibroServiceImpl implements LibroService{
	@Autowired
	private LibroRepository libroR;
	
	@Autowired
	private AutorRepository autorR;

	@Override
	public List<Libro> list_all() {
		return libroR.findAll();
	}

	@Override
	public Libro save(Libro libro) {
		/**
		Autor autor = autorR.findById(libro.getAutorId()).orElseThrow(null);
		Libro new_libro = new Libro();
		if(autor != null) {
			new_libro.setTitulo(libro.getTitulo());
			new_libro.setAutor(autor);
			new_libro.setAnio_publicacion(libro.getAnioPublicacion());
			new_libro.setEditorial(libro.getEditorial());
			new_libro.setCategoria(libro.getCategoria());
			return libroR.save(new_libro);
		}
		return new Libro();**/
		return libroR.save(libro);
	}

	@Override
	@Transactional
	public Libro update(Libro libro, Long id)throws EntityNotFoundException, IllegalOperationException {
		/**
		Autor autor = autorR.findById(libro.getAutorId()).orElseThrow(null);
		Libro new_libro = new Libro();
		if(autor != null) {
			new_libro.setId(id);
			new_libro.setTitulo(libro.getTitulo());
			new_libro.setAutor(autor);
			new_libro.setAnio_publicacion(libro.getAnioPublicacion());
			new_libro.setEditorial(libro.getEditorial());
			new_libro.setCategoria(libro.getCategoria());
			return libroR.save(new_libro);
		}
		return new Libro();**/
		Optional<Libro> libroOld = libroR.findById(id);
		if(!libroOld.isPresent())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.LIBRO_NOT_FOUND);
		libro.setIdLibro(id);		
		return libroR.save(libro);
	}

	@Override
	@Transactional
	public void delete(Long id) throws EntityNotFoundException, IllegalOperationException {
		libroR.findById(id).orElseThrow(
				()->new EntityNotFoundException(EntityNotFoundExceptionMessages.LIBRO_NOT_FOUND)
				);					
		libroR.deleteById(id);
	}

	@Override
	@Transactional
	public Libro findById(Long id) throws EntityNotFoundException {
		Optional<Libro> libro = libroR.findById(id);
		if(libro.isEmpty())throw new EntityNotFoundException(EntityNotFoundExceptionMessages.LIBRO_NOT_FOUND);
		return libro.get();
	}

	@Override
	@Transactional
	public Libro assignAuthor(Long libroId, Long autorId) throws EntityNotFoundException {
		Autor autor= autorR.findById(autorId).orElseThrow(
				()-> new EntityNotFoundException(EntityNotFoundExceptionMessages.AUTOR_NOT_FOUND)
				);
		
		Libro libro= libroR.findById(autorId).orElseThrow(
				()-> new EntityNotFoundException(EntityNotFoundExceptionMessages.LIBRO_NOT_FOUND)
				);
		
		libro.setIdLibro(libroId);
		libro.setAutor(autor);
		return libroR.save(libro);
	}
	
	
}
