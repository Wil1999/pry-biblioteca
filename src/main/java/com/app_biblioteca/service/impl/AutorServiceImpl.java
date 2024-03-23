package com.app_biblioteca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app_biblioteca.domain.Autor;
import com.app_biblioteca.repository.AutorRepository;
import com.app_biblioteca.service.AutorService;

@Service
public class AutorServiceImpl implements AutorService{

	@Autowired
	public AutorRepository autorR;
	
	@Override
	public List<Autor> list_all() {
		return autorR.findAll();
	}

	@Override
	public Autor save(Autor autor) {
		return autorR.save(autor);
	}

	@Override
	public Autor update(Autor autor, Long id) {
		autor.setId(id);
		return autorR.save(autor);
	}

	@Override
	public void delete(Long id) {
		autorR.deleteById(id);
	}

}
