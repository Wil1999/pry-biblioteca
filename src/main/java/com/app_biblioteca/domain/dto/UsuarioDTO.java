package com.app_biblioteca.domain.dto;

import java.util.List;

import com.app_biblioteca.domain.Prestamo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {

	public Long idUsuario;
	
	@Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
	@NotBlank(message = "El nombre no puede estar en blanco")
	public String nombre;
	
	@Size(min = 3, message = "El apellido paterno debe tener al menos 3 caracteres")
	@NotBlank(message = "El apellido paterno no puede estar en blanco")
	public String apellidoPat;
	
	@Size(min = 3, message = "El apellido materno debe tener al menos 3 caracteres")
	@NotBlank(message = "El apellido materno no puede estar en blanco")
	public String apellidoMat;
	
	@Email(message = " El email no cumple con una estructura valida")
	@NotBlank(message = "El email no puede estar en blanco")
	public String email;
	
	@NotBlank(message = "La direccion no puede estar en blanco")
	public String direccion;
	
	@JsonIgnore
	public List<Prestamo> prestamos;
}
