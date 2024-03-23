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

import com.app_biblioteca.domain.Libro;
import com.app_biblioteca.domain.dto.LibroDTO;
import com.app_biblioteca.service.LibroService;

@RestController
@RequestMapping("/api/v1/libro")
public class LibroController {
	
	@Autowired
	private LibroService libroS;
	
	@GetMapping
	public List<Libro> listAll(){
		return libroS.list_all();
	}
	
	@PostMapping
	public Libro save(@RequestBody LibroDTO libro) {
		return libroS.save(libro);
	}
	
	@PutMapping("/{id}")
	public Libro update(@PathVariable Long id,@RequestBody LibroDTO libro) {
		return libroS.update(libro, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		libroS.delete(id);
	}
}
