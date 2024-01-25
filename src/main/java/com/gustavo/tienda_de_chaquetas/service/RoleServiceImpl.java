package com.gustavo.tienda_de_chaquetas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.tienda_de_chaquetas.dao.api.RoleRepository;
import com.gustavo.tienda_de_chaquetas.model.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public void guardar(Role role) {
		roleRepository.save(role);
		
	}

	@Override
	public void actualizar(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void eliminar(Role role) {
		roleRepository.delete(role);
	}

	@Override
	public Role encontrarRoleById(int id) {
		return roleRepository.findById(id);
	}
	
	//@Override
	//public Role encontrarRoleByNombre(String nombre) {
		//return roleRepository.findByNombre(nombre);
	//	return null;
	//}

}
