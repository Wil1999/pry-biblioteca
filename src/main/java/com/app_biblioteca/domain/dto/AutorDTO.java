package com.app_biblioteca.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.app_biblioteca.domain.Libro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutorDTO {
	
	public Long idAutor;
	@Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
	@NotBlank(message = "El nombre no puede estar en blanco")
	public String nombres;
	@Size(min = 3, message = "El apellido paterno debe tener al menos 3 caracteres")
	@NotBlank(message = "El apellido paterno no puede estar en blanco")
	public String apellidoPat;
	@Size(min = 3, message = "El apellido materno debe tener al menos 3 caracteres")
	@NotBlank(message = "El apellido materno no puede estar en blanco")
	public String apellidoMat;
	public String biografia;
	
	@JsonIgnore
	public List<Libro> libros;
}
