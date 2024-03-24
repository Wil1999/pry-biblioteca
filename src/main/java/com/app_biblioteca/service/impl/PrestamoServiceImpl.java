package com.app_biblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app_biblioteca.domain.Libro;
import com.app_biblioteca.domain.Prestamo;
import com.app_biblioteca.domain.Usuario;
import com.app_biblioteca.domain.dto.PrestamoDTO;
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.exception.EntityNotFoundExceptionMessages;
import com.app_biblioteca.exception.IllegalOperationException;
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
	public Prestamo findById(Long id) throws EntityNotFoundException {
		Optional<Prestamo> prestamo = prestamoR.findById(id);
		if(prestamo.isEmpty())throw new EntityNotFoundException(EntityNotFoundExceptionMessages.PRESTAMO_NOT_FOUND);
		return prestamo.get();
	}

	@Override
	@Transactional
	public Prestamo save(PrestamoDTO prestamoDTO)throws EntityNotFoundException  {
		Usuario user = usuarioR.findById(prestamoDTO.getUsuarioId()).orElseThrow(
				()-> new EntityNotFoundException(EntityNotFoundExceptionMessages.USUARIO_NOT_FOUND)
				);
		Libro libro = libroR.findById(prestamoDTO.getLibroId()).orElseThrow(
				()-> new EntityNotFoundException(EntityNotFoundExceptionMessages.LIBRO_NOT_FOUND)
				);;
				
		Prestamo new_prestamo = new Prestamo();
		new_prestamo.setUsuario(user);
		new_prestamo.setLibro(libro);
		new_prestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
		new_prestamo.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
		return prestamoR.save(new_prestamo);
	}

	@Override
	@Transactional
	public Prestamo update(PrestamoDTO prestamoDTO, Long id) throws EntityNotFoundException  {
		Optional<Prestamo> prestamoFinded = prestamoR.findById(id);
		if(!prestamoFinded.isPresent())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.PRESTAMO_NOT_FOUND);
			
		Usuario user = usuarioR.findById(prestamoDTO.getUsuarioId()).orElseThrow(
				()-> new EntityNotFoundException(EntityNotFoundExceptionMessages.USUARIO_NOT_FOUND)
				);
		Libro libro = libroR.findById(prestamoDTO.getLibroId()).orElseThrow(
				()-> new EntityNotFoundException(EntityNotFoundExceptionMessages.LIBRO_NOT_FOUND)
				);;
				
		Prestamo new_prestamo = new Prestamo();
		new_prestamo.setIdPrestamo(id);
		new_prestamo.setUsuario(user);
		new_prestamo.setLibro(libro);
		new_prestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
		new_prestamo.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
		return prestamoR.save(new_prestamo);
	}

	@Override
	@Transactional
	public void delete(Long id) throws EntityNotFoundException {
		Prestamo prestamo = prestamoR.findById(id).orElseThrow(
				()->new EntityNotFoundException(EntityNotFoundExceptionMessages.PRESTAMO_NOT_FOUND)
				);			
		prestamoR.deleteById(id);
	}

	
}
