package com.gustavo.tienda_de_chaquetas.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Welcome {
	
	@GetMapping("welcome")
	public String welcome(@AuthenticationPrincipal User user) {
		System.out.println("Usuario que se autenticó: " + user);
		return "paginaAterrizaje";
	}

}
