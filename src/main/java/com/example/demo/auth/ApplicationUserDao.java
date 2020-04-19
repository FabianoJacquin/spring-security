package com.example.demo.auth;

/*
Interfaccia che fornisce il metodo per ricavare l'utente partendo dallo
username.
Il metodo selectApplicationUserByUserName restituisce un'Optional, che è
un contenitore che fornisce dei metodi per interagire con l'oggetto in esso
contenuto
 */

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUserName(String username);

}
