package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
Override del metodo UserDetailsService (come recupero l'utente dal db) e annotare con
@Bean (in maniera tale che Spring crei automaticamente un'istanza). Non essendoci un db uso in memory hash map
creo un utente (utilizzando la classe User di spring framework) con username, password e ROLE.
Il metodo restituisce un InMemoryUserDetailsManager
Il ruolo STUDENT internamente da spring viene visto come ROLE_STUDENT
La password deve essere codificata (ricevo un errore che mi segnala che la password non è
codificata), utilizzo quindi PasswordEncoder (vedi classe PasswordConfig).
Per utilizzare PasswordEncoder faccio @Autowired (verrà utilizzato il Bean che restituisce un
oggetto PasswordEncoder della classe PasswordConfig) e codifico la password nel metodo userDetailsService
 */

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails annaSmithUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(
                annaSmithUser
        );

    }
}
