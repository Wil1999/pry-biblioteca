package com.app_biblioteca.domain;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Libro{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String titulo;
	
	@ManyToOne()
	public Autor autor;
	
	public Integer anio_publicacion;
	
	public String editorial;
	
	public String categoria;
	
	@OneToMany(mappedBy = "libro")
	public List<Prestamo> prestamos;
}
