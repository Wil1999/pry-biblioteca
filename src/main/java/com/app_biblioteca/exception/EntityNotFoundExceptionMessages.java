package com.app_biblioteca.exception;

public class EntityNotFoundExceptionMessages {
	
	// Messages to errors entities
	public static final String AUTOR_NOT_FOUND = "El AUTOR con el ID proporcionado no fue encontrado";
	public static final String LIBRO_NOT_FOUND = "El LIBRO con el ID proporcionado no fue encontrado";
	public static final String PRESTAMO_NOT_FOUND = "El PRESTAMO con el ID proporcionado no fue encontrado";
	public static final String USUARIO_NOT_FOUND = "El USUARIO con el ID proporcionado no fue encontrado";
	
	public static final String AUTORES_NOT_FOUND = "No hay registro de AUTORES";
	
	
	// Constructor privado
		private EntityNotFoundExceptionMessages() {
			throw new IllegalStateException ("Utility class");
		}
}
