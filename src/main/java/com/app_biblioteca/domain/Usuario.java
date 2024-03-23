package com.app_biblioteca.domain;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String nombre;
	
	public String apellidoPat;
	
	public String apellidoMat;
	
	@Column(unique = true)
	public String email;
	
	public String direccion;
	
	@OneToMany(mappedBy = "usuario")
	public List<Prestamo> prestamos;
}
