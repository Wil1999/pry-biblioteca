package com.app_biblioteca.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app_biblioteca.domain.Autor;
import com.app_biblioteca.domain.dto.AutorDTO;
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.exception.IllegalOperationException;
import com.app_biblioteca.service.AutorService;
import com.app_biblioteca.util.ApiModelResponse;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/autor")
public class AutorController {
	
	@Autowired
	private AutorService autorS;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<?> listAll() {
		List<Autor> autores = autorS.list_all();
    	if(autores==null || autores.isEmpty()) {
    		return ResponseEntity.noContent().build();
    	}
    	else {     
    		List<AutorDTO> autoresDTOs = autores.stream()
    				.map(aut -> modelMapper.map(aut, AutorDTO.class))
    				.collect(Collectors.toList());
    		ApiModelResponse<List<AutorDTO>> response = new ApiModelResponse<>(true, "Lista de autores obtenidas con éxito", autoresDTOs);
    		return ResponseEntity.ok(response);
    	}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) throws EntityNotFoundException {
		Autor autor = autorS.findById(id);
    	AutorDTO autorDTO = modelMapper.map(autor, AutorDTO.class);
    	ApiModelResponse<AutorDTO> response = new ApiModelResponse<>(true, "Datos del autor obtenida con éxito", autorDTO);
    	return ResponseEntity.ok(response);
	}
	
	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody AutorDTO autorDTO){
		System.out.println("TEST");
    	Autor autor = modelMapper.map(autorDTO, Autor.class);
    	autorS.save(autor);
    	AutorDTO autorDTOSaved = modelMapper.map(autor, AutorDTO.class);
    	ApiModelResponse<AutorDTO> response = new ApiModelResponse<>(true, "Datos del autor grabados con éxito", autorDTOSaved);
    	return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody AutorDTO autorDTO) throws  EntityNotFoundException {
		Autor autor = modelMapper.map(autorDTO, Autor.class);
    	autorS.update(autor,id);
    	AutorDTO autorDTOUpdated = modelMapper.map(autor, AutorDTO.class);
    	ApiModelResponse<AutorDTO> response = new ApiModelResponse<>(true, "Datos del autor actualizados con éxito", autorDTOUpdated);
    	return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException  {
		autorS.delete(id);
		ApiModelResponse<?> response = new ApiModelResponse<>(true,"Autor eliminado con éxito", null);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
