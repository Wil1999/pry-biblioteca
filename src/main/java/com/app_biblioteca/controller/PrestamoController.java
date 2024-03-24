package com.app_biblioteca.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.service.PrestamoService;
import com.app_biblioteca.util.ApiModelResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/prestamo")
public class PrestamoController {
	
	@Autowired
	private PrestamoService prestamoS;
	
	@GetMapping
	public List<Prestamo> listAll(){
		return prestamoS.list_all();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) throws EntityNotFoundException{
		Prestamo prestamo = prestamoS.findById(id);
    	ApiModelResponse<Prestamo> response = new ApiModelResponse<>(true, "Datos del préstamo obtenidos con éxito", prestamo);
    	return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody PrestamoDTO prestamo) throws EntityNotFoundException{
		Prestamo prestamoN = prestamoS.save(prestamo);
    	ApiModelResponse<Prestamo> response = new ApiModelResponse<>(true, "Datos del préstamo grabados con éxito", prestamoN);
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody PrestamoDTO prestamo) throws EntityNotFoundException {
		Prestamo prestamoN = prestamoS.update(prestamo, id);
    	ApiModelResponse<Prestamo> response = new ApiModelResponse<>(true, "Datos del préstamo actualizados con éxito", prestamoN);
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws EntityNotFoundException {
		prestamoS.delete(id);
    	ApiModelResponse<?> response = new ApiModelResponse<>(true, "Datos de préstamo eliminado con éxito", null);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
