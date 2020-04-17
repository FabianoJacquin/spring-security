package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
Creo una classe @Configuration con un metodo passwordEncoder che restituisce un oggetto che implementa
PasswordEncoder. Tra i vari oggetti che implementano l'interfaccia c'è BCryptPasswordEncoder il cui
metodo encode prende un intero (strength) che significa -> la forza con la quale codifico la password
Il metodo viene annotato con @Bean così da istanziarlo all'avvio
 */

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(10);

    }

}
