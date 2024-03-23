package com.app_biblioteca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app_biblioteca.domain.Libro;
import com.app_biblioteca.domain.Prestamo;
import com.app_biblioteca.domain.Usuario;
import com.app_biblioteca.domain.dto.PrestamoDTO;
import com.app_biblioteca.repository.LibroRepository;
import com.app_biblioteca.repository.PrestamoRepository;
import com.app_biblioteca.repository.UsuarioRepository;
import com.app_biblioteca.service.PrestamoService;

import jakarta.transaction.Transactional;

@Service
public class PrestamoServiceImpl implements PrestamoService{

	@Autowired
	private PrestamoRepository prestamoR;
	
	@Autowired
	private UsuarioRepository usuarioR;
	
	@Autowired
	private LibroRepository libroR;
	
	@Override
	public List<Prestamo> list_all() {
		return prestamoR.findAll();
	}

	@Override
	@Transactional
	public Prestamo save(PrestamoDTO prestamo) {
		Usuario user = usuarioR.findById(prestamo.getUserId()).orElseThrow(null);
		Libro libro = libroR.findById(prestamo.getLibroId()).orElseThrow(null);
		Prestamo new_prestamo = new Prestamo();
		if(user != null && libro != null) {
			new_prestamo.setUsuario(user);
			new_prestamo.setLibro(libro);
			new_prestamo.setFechaPrestamo(prestamo.getFechaPrestamo());
			new_prestamo.setFechaDevolucion(prestamo.getFechaDevolucion());
			return prestamoR.save(new_prestamo);
		}
		return new Prestamo();
	}

	@Override
	@Transactional
	public Prestamo update(PrestamoDTO prestamo, Long id) {
		Usuario user = usuarioR.findById(prestamo.getUserId()).orElseThrow(null);
		Libro libro = libroR.findById(prestamo.getLibroId()).orElseThrow(null);
		Prestamo new_prestamo = new Prestamo();
		if(user != null && libro != null) {
			new_prestamo.setId(id);
			new_prestamo.setUsuario(user);
			new_prestamo.setLibro(libro);
			new_prestamo.setFechaPrestamo(prestamo.getFechaPrestamo());
			new_prestamo.setFechaDevolucion(prestamo.getFechaDevolucion());
			return prestamoR.save(new_prestamo);
		}
		return new Prestamo();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		prestamoR.deleteById(id);
	}

	
}
