package com.app_biblioteca.service;

import java.util.List;

import com.app_biblioteca.domain.Usuario;
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.exception.IllegalOperationException;

public interface UsuarioService {
	List<Usuario> list_all();
	Usuario findById(Long id)throws EntityNotFoundException;
	Usuario save(Usuario user) throws IllegalOperationException; //Operation in case email attribute repeat 
	Usuario update(Usuario user, Long id) throws EntityNotFoundException, IllegalOperationException;
	void delete(Long id) throws EntityNotFoundException, IllegalOperationException;
	
}
