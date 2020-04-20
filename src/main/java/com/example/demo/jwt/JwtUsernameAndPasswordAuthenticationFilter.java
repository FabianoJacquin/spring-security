package com.example.demo.jwt;

/*
Classe che si occupa della verifica e validazione delle credenziali ricevute dal client.
Estendere UsernamePasswordAuthenticationFilter e fare @Override del metodo attemptAuthentication
che come parametri prende request e response.
La prima cose che deve fare attemptAuthentication è di prendere username e password. A questo scopo
creo classe UsernameAndPasswordAuthenticationRequest (si tratta semplicemente di un POJO per gestire
username e password).
Per inserire username e password all'interno della classe UsernameAndPasswordAuthenticationRequest
uso ObjectMapper.
Per verificare se username e password sono corretti uso AuthenticationManager e il suo unico metodo
authenticate che prende come paramentro un oggetto Authentication. Si tratta di un'interfaccia
che è implementata da diverse classi. Quella che ci interessa è UsernamePasswordAuthenticationToken
che richiede due parametri: principal (username) e credential (password).
authenticationManager verifica che l'utente esista e se esiste che la password sia corretta.

 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            Authentication authenticate = authenticationManager.authenticate(authentication);

            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
