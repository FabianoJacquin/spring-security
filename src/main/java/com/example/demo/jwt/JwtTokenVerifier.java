package com.example.demo.jwt;

/*
Il compito di questo filtro è di verificare se il token ricevuto dal client
è valido e passarlo al prossimo filtro (i filtri sono in serie) oppure passarlo
alla risorsa richiesta o se non valido lanciare un eccezzione.
Estende la classe OncePerRequestFilter in quanto deve essere eseguito una sola
volta per request
La prima cosa da fare è ottenere il token dalla richiesta. Se il token che ricavo dall'header
è null oppure con inizia con Bearer_ la richiesta viene rigettata.
Dopo aver creato il filtro bisogna registrarlo in ApplicationSecurityConfig
Perchè la catena di filtri funzioni bisogna passare request response a quello
successivo con filterChain.doFilter(request, response);
 */

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if(Strings.isNullOrEmpty(authorizationHeader) || authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token =  authorizationHeader.replace("Bearer ", "");

        try {

            String secretKey = "questachiavedeveessereveramentemoltosicuramamoltomoltosicura";

            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();

            String username = body.getSubject();

            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e){
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

        filterChain.doFilter(request, response);

    }
}
