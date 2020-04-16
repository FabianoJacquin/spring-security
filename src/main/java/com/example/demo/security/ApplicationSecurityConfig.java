package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
@Configuration dico a Spring Boot di istanziare all'avvio questa classe e che si tratta di una classe
di configurazione
@EnableWebSecurity dico a Spring che questa è la classe nella quale viene configurato Spring Security per tutta
l'applicazione
Devo estendere WebSecurityConfigurerAdapter e fare l'override del metodo configure. All'interno di questo metodo
scrivo il codice di comnfigurazione

authorizeRequests voglio autorizzare le richieste
anyRequest tutte le richieste (username a pwd vengono inviati ad ogni richiesta)
authenticated devono essere autenticate (specificando username e password)
and e
httpBasic usando basic authentication

 */

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
