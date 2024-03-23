package com.app_biblioteca.domain;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@ManyToOne()
	public Usuario usuario;
	
	@ManyToOne()
	public Libro libro;
	
	@Temporal(TemporalType.DATE)
	public Date fechaPrestamo;
	
	@Temporal(TemporalType.DATE)
	public Date fechaDevolucion;
}
