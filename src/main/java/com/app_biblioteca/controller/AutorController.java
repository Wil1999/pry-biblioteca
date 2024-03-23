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

import com.app_biblioteca.domain.Autor;
import com.app_biblioteca.service.AutorService;

@RestController
@RequestMapping("/api/v1/autor")
public class AutorController {
	
	@Autowired
	private AutorService autorS;
	
	@GetMapping
	public List<Autor> listAll() {
		return autorS.list_all();
	}
	
	@PostMapping()
	public Autor save(@RequestBody Autor autor) {
		return autorS.save(autor);
	}
	
	@PutMapping("/{id}")
	public Autor update(@PathVariable Long id,@RequestBody Autor autor) {
		return autorS.update(autor, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		autorS.delete(id);
	}
}
