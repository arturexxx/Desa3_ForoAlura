package com.alura.Desafio3ForoAlura.controller;


import com.alura.Desafio3ForoAlura.domain.usuarios.Usuario;
import com.alura.Desafio3ForoAlura.infra.security.DatosJWTtoken;
import com.alura.Desafio3ForoAlura.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DatosAutenticacion datos) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
        var usuarioAutenticado = manager.authenticate(authToken);
        var JWTtoken = tokenService.generartoken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }

    public record DatosAutenticacion(String login, String contrasena) {
    }

}


