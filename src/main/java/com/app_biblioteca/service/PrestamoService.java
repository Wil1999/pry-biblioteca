package com.app_biblioteca.service;

import java.util.List;

import com.app_biblioteca.domain.Prestamo;
import com.app_biblioteca.domain.dto.PrestamoDTO;

public interface PrestamoService {

	List<Prestamo> list_all();
	Prestamo save(PrestamoDTO prestamo);
	Prestamo update(PrestamoDTO prestamo, Long id);
	void delete(Long id);
}
