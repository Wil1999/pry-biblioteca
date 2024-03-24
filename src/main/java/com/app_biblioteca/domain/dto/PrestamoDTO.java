package com.app_biblioteca.domain.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.app_biblioteca.domain.Libro;
import com.app_biblioteca.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class PrestamoDTO {
	
	public Long idPrestamo;
	
	@PastOrPresent
	@NotNull(message = "La fecha de prestamo no puede estar vacía o no sigue el patron yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date fechaPrestamo;
	
	@Future
	@NotNull(message = "La fecha de devolucion no puede estar vacía o no sigue el patron yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date fechaDevolucion;
	
	@NotNull(message = "Tiene que haber una asignacion a algun usuario")
	public Long usuarioId;
	
	@NotNull(message = "Tiene que haber una asignacion a algun libro")
	public Long libroId;
}
