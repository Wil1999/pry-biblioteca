package com.app_biblioteca.domain.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.app_biblioteca.domain.Autor;
import com.app_biblioteca.domain.Prestamo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LibroDTO {
	
	public Long idLibro;
	
	@NotBlank(message = "El titulo no puede estar en blanco")
	@Size(min = 3, message = "El titulo del libro debe tener al menos 3 caracteres")
	public String titulo;
	
	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "La fecha de publicacion no puede estar vacía o no sigue el patron yyyy-MM-dd")
	public Date anioPublicacion;
	
	@NotBlank(message = "El nombre de editorial no puede estar en blanco")
	@Size(min = 3, message = "El titulo del libro debe tener al menos 3 caracteres")
	public String editorial;
	
	@Pattern(regexp = "^(FICCION|AUTOAYUDA|NUTRICION|COCINA|MANGAS|FABULAS)$", message = "Las categorias permitidos son: FICCION|AUTOAYUDA|NUTRICION|COCINA|MANGAS|FABULAS ")
	@Pattern(regexp = "^[A-Z]*$", message = "Los categorias deben estar en mayuscula")
	public String categoria;
	
	@JsonIgnore
	public Autor autor;
	
	@JsonIgnore
	public List<Prestamo> prestamos;
}
