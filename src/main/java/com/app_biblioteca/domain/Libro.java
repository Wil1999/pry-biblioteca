package com.app_biblioteca.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="idLibro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idLibro;
	
	public String titulo;
	
	@ManyToOne
	@JsonBackReference
	public Autor autor;
	
	@Temporal(TemporalType.DATE)
	public Date anio_publicacion;
	
	public String editorial;
	
	public String categoria;
	
	@OneToMany(mappedBy = "libro",fetch = FetchType.LAZY)
	@JsonManagedReference
	public List<Prestamo> prestamos;
}
