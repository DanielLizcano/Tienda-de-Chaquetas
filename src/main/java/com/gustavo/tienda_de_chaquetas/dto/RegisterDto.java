package com.gustavo.tienda_de_chaquetas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterDto {
	
	@NotEmpty(message = "El campo Nombre no debe quedar vacio")
	private String nombre;
	
    @NotEmpty(message = "El correo no puede estar vacío")
    @Email(message = "Correo electrónico no válido")
    private String correo;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
    
    private String telefono;
    
    private String nombre_empresa;
    
    private String tipo;
}
