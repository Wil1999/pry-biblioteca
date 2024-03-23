package com.app_biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app_biblioteca.domain.Prestamo;
import com.app_biblioteca.domain.dto.PrestamoDTO;
import com.app_biblioteca.service.PrestamoService;

@RestController
@RequestMapping("/api/v1/prestamo")
public class PrestamoController {
	
	@Autowired
	private PrestamoService prestamoS;
	
	@GetMapping
	public List<Prestamo> listAll(){
		return prestamoS.list_all();
	}
	
	@PostMapping
	public Prestamo save(@RequestBody PrestamoDTO prestamo) {
		return prestamoS.save(prestamo);
	}
	
	@PutMapping("/{id}")
	public Prestamo update(@PathVariable Long id,@RequestBody PrestamoDTO prestamo) {
		return prestamoS.update(prestamo, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		prestamoS.delete(id);
	}
}
