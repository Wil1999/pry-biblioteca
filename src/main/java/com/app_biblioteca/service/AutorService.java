package com.app_biblioteca.service;

import java.util.List;

import com.app_biblioteca.domain.Autor;

public interface AutorService {
	
	List<Autor> list_all();
	Autor save(Autor autor);
	Autor update(Autor autor, Long id);
	void delete(Long id);

}
