package com.gustavo.tienda_de_chaquetas.controllers;

import java.util.Collections;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.gustavo.tienda_de_chaquetas.dao.api.PersonaRepository;
//import com.gustavo.tienda_de_chaquetas.dao.api.RoleRepository;
import com.gustavo.tienda_de_chaquetas.dto.LoginDto;
import com.gustavo.tienda_de_chaquetas.dto.RegisterDto;
import com.gustavo.tienda_de_chaquetas.model.Persona;
import com.gustavo.tienda_de_chaquetas.model.Role;
import com.gustavo.tienda_de_chaquetas.security.CustomUserDetailsService;
import com.gustavo.tienda_de_chaquetas.service.PersonaService;
import com.gustavo.tienda_de_chaquetas.service.RoleService;

//import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("api/auth")
public class PersonaController {

	@Autowired
    private PersonaService personaService;
    private RoleService roleService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    
	public PersonaController(AuthenticationManager authenticationManager,
                             PersonaService personaService,
                             RoleService roleService,
                             PasswordEncoder passwordEncoder) {

        this.personaService = personaService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;

        System.out.print("Rol no existente");
        System.out.print(roleService.encontrarRoleById(3));
        System.out.print("-------------------------------");

        if(roleService.encontrarRoleById(1) == null){
        	roleService.guardar(new Role(1,"ROLE_ADMIN"));
        }
        
        if(roleService.encontrarRoleById(2) == null){
        	roleService.guardar(new Role(2,"ROLE_USER"));
        }

	}

    public static long generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        return mostSignificantBits ^ leastSignificantBits;
    }

/*
 *  ########  ########  ######   ####  ######  ######## ######## ########  
    ##     ## ##       ##    ##   ##  ##    ##    ##    ##       ##     ## 
    ##     ## ##       ##         ##  ##          ##    ##       ##     ## 
    ########  ######   ##   ####  ##   ######     ##    ######   ########  
    ##   ##   ##       ##    ##   ##        ##    ##    ##       ##   ##   
    ##    ##  ##       ##    ##   ##  ##    ##    ##    ##       ##    ##  
    ##     ## ########  ######   ####  ######     ##    ######## ##     ##
 */

    @PostMapping("/registro")
    public String procesarFormularioRegistro(@Valid RegisterDto registerDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formularioRegistro";
        }
        
        System.out.print(registerDto);
        
        this.register(registerDto);

        // Lógica para procesar el registro si la validación es exitosa

        return "redirect:/formularios/login";
    }


    @PostMapping ("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
    	
    	// Validamos si el usuario ya existe
        Persona persona = new Persona();
        persona.setCorreo(registerDto.getCorreo());
        if(personaService.encontrarPersona(persona) != null){
            return new ResponseEntity<>("El usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        
        // Insertamos el usuario si no existe
        Persona user = new Persona();
        user.setId(PersonaController.generateUniqueId());
        user.setNombre(registerDto.getNombre());
        user.setCorreo(registerDto.getCorreo());
        user.setTelefono(registerDto.getTelefono());
        user.setNombre_empresa(registerDto.getNombre_empresa());
        user.setTipo_usuario(registerDto.getTipo());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        // Consultamos el rol que se le va asignar
        
        int id = registerDto.getTipo().equals("vendedor") ? 1 : 2;
        
        Role roles = roleService.encontrarRoleById(id);
        user.setRoles(Collections.singletonList(roles));
        
        personaService.guardar(user);
        
        return new ResponseEntity<String>("User registered success!", HttpStatus.OK);

    }


/*
  * ##        #######   ######   #### ##    ## 
    ##       ##     ## ##    ##   ##  ###   ## 
    ##       ##     ## ##         ##  ####  ## 
    ##       ##     ## ##   ####  ##  ## ## ## 
    ##       ##     ## ##    ##   ##  ##  #### 
    ##       ##     ## ##    ##   ##  ##   ### 
    ########  #######   ######   #### ##    ## 
*/

    @PostMapping("login")
    public String login(@Valid LoginDto loginDto, BindingResult result, Model model){
        System.out.println(loginDto);

        if (result.hasErrors()) {
            return "formularioLogin";
        }

        CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService(personaService);

        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDto.getCorreo(),
                    loginDto.getPassword(),
                    customUserDetailsService.loadUserByUsername(loginDto.getCorreo()).getAuthorities()
                )
            );


            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/welcome";

        }catch (Exception e) {
            // Manejar excepción de autenticación
            System.err.println("Error en el inicio de sesión para el usuario: " + loginDto.getCorreo() + ". Detalles: " + e);
            return "formularioLogin";
        }
    }



   


    
}
