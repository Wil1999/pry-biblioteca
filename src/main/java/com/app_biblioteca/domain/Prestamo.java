package com.app_biblioteca.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="idPrestamo")
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idPrestamo;
	
	@ManyToOne()
	@JsonBackReference
	public Usuario usuario;
	
	@ManyToOne()
	@JsonBackReference
	public Libro libro;
	
	@Temporal(TemporalType.DATE)
	public Date fechaPrestamo;
	
	@Temporal(TemporalType.DATE)
	public Date fechaDevolucion;
}
