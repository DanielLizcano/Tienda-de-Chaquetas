package com.gustavo.tienda_de_chaquetas.service;

import java.util.List;

import com.gustavo.tienda_de_chaquetas.model.Role;

public interface RoleService {
	
	public List<Role> findAll();
	
	public void guardar(Role role);
	
	public void actualizar(Role role);
	
	public void eliminar(Role role);
	
	public Role encontrarRoleById(int id);
	
	//public Role encontrarRoleByNombre(String nombre);

}
