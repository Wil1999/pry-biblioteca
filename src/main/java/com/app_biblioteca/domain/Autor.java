package com.app_biblioteca.domain;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	public String nombres;
	
	public String apellidoPat;
	
	public String apellidoMat;
	
	@Lob
	public String biografia;
	
	@OneToMany(mappedBy = "autor")
	public List<Libro> libros = new ArrayList<>();
}
