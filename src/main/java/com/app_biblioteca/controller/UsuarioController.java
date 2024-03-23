package com.app_biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app_biblioteca.domain.Usuario;
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.exception.IllegalOperationException;
import com.app_biblioteca.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioS;
	
	@GetMapping
	public List<Usuario> listAll(){
		return usuarioS.list_all();
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Usuario usuario) throws IllegalOperationException {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioS.save(usuario));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Usuario usuario) throws EntityNotFoundException, IllegalOperationException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioS.update(usuario, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
		Optional<Usuario> u = Optional.of(usuarioS.findById(id));
		if(u.isPresent()){
			usuarioS.delete(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
