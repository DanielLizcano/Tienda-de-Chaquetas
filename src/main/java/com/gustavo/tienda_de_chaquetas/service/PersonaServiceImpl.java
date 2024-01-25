package com.gustavo.tienda_de_chaquetas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.tienda_de_chaquetas.dao.api.PersonaRepository;
import com.gustavo.tienda_de_chaquetas.model.Persona;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;
	
	public PersonaServiceImpl(PersonaRepository personaRepository) {
		this.personaRepository = personaRepository;
	}
	
	@Override
	public List<Persona> findAll() {
		return (List<Persona>)personaRepository.findAll();
	}

	@Override
	public void guardar(Persona persona) {
		this.personaRepository.save(persona);
		
	}

	@Override
	public void actualizar(Persona persona) {
		this.personaRepository.save(persona);
	}

	@Override
	public void eliminar(Persona persona) {
		this.personaRepository.delete(persona);
	}

	@Override
	public Persona encontrarPersona(Persona persona) {
		Persona result = this.personaRepository.findByCorreo(persona.getCorreo());
		return result;
	}

}
