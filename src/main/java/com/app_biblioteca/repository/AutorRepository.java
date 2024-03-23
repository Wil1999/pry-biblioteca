package com.app_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app_biblioteca.domain.Autor;

public interface AutorRepository extends JpaRepository<Autor,Long>{

}
