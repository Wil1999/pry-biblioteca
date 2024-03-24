package com.app_biblioteca.service;

import java.util.List;

import com.app_biblioteca.domain.Prestamo;
import com.app_biblioteca.domain.dto.PrestamoDTO;
import com.app_biblioteca.exception.EntityNotFoundException;

public interface PrestamoService {

	List<Prestamo> list_all();
	Prestamo findById(Long id) throws EntityNotFoundException;
	Prestamo save(PrestamoDTO prestamo)throws EntityNotFoundException;
	Prestamo update(PrestamoDTO prestamo, Long id) throws EntityNotFoundException;
	void delete(Long id) throws EntityNotFoundException;
}
