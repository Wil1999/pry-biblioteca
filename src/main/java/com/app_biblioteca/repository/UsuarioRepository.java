package com.app_biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app_biblioteca.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
	Optional<Usuario> findByEmail(String email);
}
