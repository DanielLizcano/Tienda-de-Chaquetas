package com.gustavo.tienda_de_chaquetas.service;

import java.util.List;

import com.gustavo.tienda_de_chaquetas.model.Persona;

public interface PersonaService {
	
	public List<Persona> findAll();
	
	public void guardar(Persona persona);
	
	public void actualizar(Persona persona);
	
	public void eliminar(Persona persona);
	
	public Persona encontrarPersona(Persona persona);

}
