package com.app_biblioteca.domain.dto;

import lombok.Data;

@Data
public class LibroDTO {
	
	public String titulo;
	public Long autorId;
	public Integer anioPublicacion;
	public String editorial;
	public String categoria;
}
