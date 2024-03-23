package com.app_biblioteca.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PrestamoDTO {
	public Long userId;
	public Long libroId;
	public Date fechaPrestamo;
	public Date fechaDevolucion;
}
