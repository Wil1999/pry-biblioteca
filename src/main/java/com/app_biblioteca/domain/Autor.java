package com.app_biblioteca.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="idAutor")
public class Autor{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idAutor;
	
	public String nombres;
	
	public String apellidoPat;
	
	public String apellidoMat;
	
	@Lob
	@Column(nullable = false)
	public String biografia;
	
	@OneToMany(mappedBy = "autor", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	public List<Libro> libros;
}
