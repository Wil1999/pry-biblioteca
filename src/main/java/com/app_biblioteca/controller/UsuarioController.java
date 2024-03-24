package com.app_biblioteca.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import com.app_biblioteca.domain.dto.UsuarioDTO;
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.exception.IllegalOperationException;
import com.app_biblioteca.service.UsuarioService;
import com.app_biblioteca.util.ApiModelResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioS;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		List<Usuario> usuarios = usuarioS.list_all();
		if(usuarios==null || usuarios.isEmpty()) {
    		return ResponseEntity.noContent().build();
    	}
    	else {     
    		List<UsuarioDTO> usuariosDTOs = usuarios.stream()
    				.map(users -> modelMapper.map(users, UsuarioDTO.class))
    				.collect(Collectors.toList());
    		ApiModelResponse<List<UsuarioDTO>> response = new ApiModelResponse<>(true, "Lista de usuarios obtenida con éxito", usuariosDTOs);
    		return ResponseEntity.ok(response);
    	}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) throws EntityNotFoundException{
		Usuario usuario = usuarioS.findById(id);
    	UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
    	ApiModelResponse<UsuarioDTO> response = new ApiModelResponse<>(true, "Datos del usuario obtenido con éxito", usuarioDTO);
    	return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody UsuarioDTO usuarioDTO) throws IllegalOperationException {
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
    	usuarioS.save(usuario);
    	UsuarioDTO savedUsuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
    	ApiModelResponse<UsuarioDTO> response = new ApiModelResponse<>(true, "Datos del usuario grabados con éxito", savedUsuarioDTO);
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody UsuarioDTO usuarioDTO) throws EntityNotFoundException, IllegalOperationException {
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
    	usuarioS.update(usuario, id);
    	UsuarioDTO updatedUsuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
    	ApiModelResponse<UsuarioDTO> response = new ApiModelResponse<>(true, "Datos del usuario actualizados con éxito", updatedUsuarioDTO);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
		usuarioS.delete(id);
    	ApiModelResponse<?> response = new ApiModelResponse<>(true, "Usuario eliminado con éxito", null);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
