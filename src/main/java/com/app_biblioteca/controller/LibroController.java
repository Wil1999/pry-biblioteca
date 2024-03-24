package com.app_biblioteca.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app_biblioteca.domain.Libro;
import com.app_biblioteca.domain.dto.LibroDTO;
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.exception.IllegalOperationException;
import com.app_biblioteca.service.LibroService;
import com.app_biblioteca.util.ApiModelResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/libro")
public class LibroController {
	
	@Autowired
	private LibroService libroS;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		List<Libro> libros = libroS.list_all();
		if(libros == null || libros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			List<LibroDTO> librosDTO = libros.stream()
										.map(lib -> modelMapper.map(lib,LibroDTO.class))
										.collect(Collectors.toList());
			ApiModelResponse<List<LibroDTO>> response = new ApiModelResponse<>(true, "Lista de libros obtenido con éxito",librosDTO);
			return ResponseEntity.ok(response);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) throws EntityNotFoundException{
		Libro libro = libroS.findById(id);
		LibroDTO libroDTO = modelMapper.map(libro, LibroDTO.class);
		ApiModelResponse<LibroDTO> response = new ApiModelResponse<>(true,"Datos del libro obtenido con éxito", libroDTO);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody LibroDTO libroDTO) throws IllegalOperationException{
		Libro libro = modelMapper.map(libroDTO,Libro.class);
		libroS.save(libro);
		LibroDTO saveDTO = modelMapper.map(libro,LibroDTO.class);
		ApiModelResponse<LibroDTO> response = new ApiModelResponse<>(true,"Datos del libro grabado con éxito",saveDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody LibroDTO libroDTO)throws IllegalOperationException, EntityNotFoundException{
		Libro libro = modelMapper.map(libroDTO,Libro.class);
		libroS.update(libro,id);
		LibroDTO saveDTO = modelMapper.map(libro,LibroDTO.class);
		ApiModelResponse<LibroDTO> response = new ApiModelResponse<>(true,"Datos del libro actualizados con éxito",saveDTO);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws IllegalOperationException, EntityNotFoundException {
		libroS.delete(id);
		ApiModelResponse<?> response = new ApiModelResponse<>(true, "Datos de libro eliminado con éxito", null);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	///
	@PatchMapping("/{libroId}/autor/{autorId}")
	public ResponseEntity<?> assignAuthor(@PathVariable Long libroId, @PathVariable Long autorId) throws EntityNotFoundException {
		libroS.assignAuthor(libroId,autorId);
		ApiModelResponse<?> response = new ApiModelResponse<>(true, "Datos actualizados", null);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
