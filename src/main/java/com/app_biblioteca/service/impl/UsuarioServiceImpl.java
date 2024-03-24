package com.app_biblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app_biblioteca.domain.Usuario;
import com.app_biblioteca.exception.EntityNotFoundException;
import com.app_biblioteca.exception.EntityNotFoundExceptionMessages;
import com.app_biblioteca.exception.IllegalOperationException;
import com.app_biblioteca.repository.UsuarioRepository;
import com.app_biblioteca.service.UsuarioService;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioR;
	
	@Override
	public List<Usuario> list_all() {
		List<Usuario> usuarios = usuarioR.findAll();
		if(usuarios.isEmpty())throw new EntityNotFoundException(EntityNotFoundExceptionMessages.USUARIO_NOT_FOUND);
		return usuarios;
	}

	@Override
	@Transactional
	public Usuario save(Usuario user)throws IllegalOperationException{
		if(!usuarioR.findByEmail(user.getEmail()).isEmpty()){
			throw new IllegalOperationException("Ya existe un USUARIO con ese EMAIL");
		}
		return usuarioR.save(user);
	}

	@Override
	@Transactional
	public Usuario update(Usuario user, Long id) throws EntityNotFoundException, IllegalOperationException{
		Optional<Usuario> invEntity = usuarioR.findById(id);
		if(invEntity.isEmpty()) {
			throw new EntityNotFoundException("El investigador no existe");
		}
		if(!usuarioR.findByEmail(user.getEmail()).isEmpty()) {
			throw new EntityNotFoundException("El investigador no existe");
		}
		user.setIdUsuario(id);
		return usuarioR.save(user);
	}

	@Override
	@Transactional
	public void delete(Long id) throws EntityNotFoundException, IllegalOperationException{
		Usuario user = usuarioR.findById(id).orElseThrow(
				() -> new EntityNotFoundException(EntityNotFoundExceptionMessages.USUARIO_NOT_FOUND)
				);
		usuarioR.deleteById(id);
	}

	@Override
	public Usuario findById(Long id) throws EntityNotFoundException {
		Optional<Usuario> usuario = usuarioR.findById(id);
		if(usuario.isEmpty()) throw new EntityNotFoundException(EntityNotFoundExceptionMessages.USUARIO_NOT_FOUND);
		return usuario.get();
	}
	
	
}
