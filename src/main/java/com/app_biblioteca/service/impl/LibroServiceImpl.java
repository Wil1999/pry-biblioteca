package com.app_biblioteca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app_biblioteca.domain.Autor;
import com.app_biblioteca.domain.Libro;
import com.app_biblioteca.domain.Prestamo;
import com.app_biblioteca.domain.Usuario;
import com.app_biblioteca.domain.dto.LibroDTO;
import com.app_biblioteca.repository.AutorRepository;
import com.app_biblioteca.repository.LibroRepository;
import com.app_biblioteca.service.LibroService;

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
	public Libro save(LibroDTO libro) {
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
		return new Libro();
	}

	@Override
	public Libro update(LibroDTO libro, Long id) {
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
		return new Libro();
	}

	@Override
	public void delete(Long id) {
		libroR.deleteById(id);
	}
	
}
