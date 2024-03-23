package com.app_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app_biblioteca.domain.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{

}
