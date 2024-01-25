package com.gustavo.tienda_de_chaquetas.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gustavo.tienda_de_chaquetas.dao.api.PersonaRepository;
import com.gustavo.tienda_de_chaquetas.model.Persona;
import com.gustavo.tienda_de_chaquetas.model.Role;
import com.gustavo.tienda_de_chaquetas.service.PersonaService;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

	@Autowired
    private PersonaService personaService;

    
    public CustomUserDetailsService(PersonaService personaService) {
    	this.personaService = personaService;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

        Persona persona = new Persona();
        persona.setCorreo(correo);
        Persona user = personaService.encontrarPersona(persona);

        return new User(user.getCorreo(), user.getPassword(), mapRolesToAuthorities(user.getRoles()) );
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}