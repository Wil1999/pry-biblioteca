package com.app_biblioteca.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="idUsuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idUsuario;
	
	public String nombre;
	
	public String apellidoPat;
	
	public String apellidoMat;
	
	@Column(unique = true)
	public String email;
	
	public String direccion;
	
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.REMOVE)
	@JsonManagedReference
	public List<Prestamo> prestamos;
}
