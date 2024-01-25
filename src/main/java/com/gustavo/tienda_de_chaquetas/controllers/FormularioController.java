package com.gustavo.tienda_de_chaquetas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gustavo.tienda_de_chaquetas.dto.LoginDto;
import com.gustavo.tienda_de_chaquetas.dto.RegisterDto;
import com.gustavo.tienda_de_chaquetas.model.Role;

@Controller
@RequestMapping("formularios")
public class FormularioController {
	
    @GetMapping("tipoRegistro")
    public String tipoRegistro() {
    	return "tipoRegistro";
    }
	
    @GetMapping("/login")
    public String mostrarFormularioLogin(LoginDto loginDto) {
        return "formularioLogin";
    }
	
	@PostMapping("/registro")
    public String mostrarFormularioRegistro(RegisterDto registerDto, @RequestParam String tipo, Model model) {
    	System.out.print("El tipo de registro es: " + tipo);
    	model.addAttribute("tipo", tipo);
        return "formularioRegistro";
    }
}
