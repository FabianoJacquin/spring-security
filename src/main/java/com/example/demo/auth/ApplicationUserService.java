package com.example.demo.auth;

/*
Questa classe di servizio implementa l'interfaccia USerDetailService
Questa interfaccia ha solo un metodo: loadUserByUsername
La classe deve essere annotata con @Service così Spring la inizializza
Il prossimo passo è quello di implementare l'iterfaccia che ci permetterà
di caricare l'utente da un qualiasi data source: ApplicationUserDao
Dopo aver creato l'interfaccia i questa classe dichiaro una variabile
final riferita all'interfaccia appena creata. Questo permetterà di switcchare
tra le varie implementazioni dell'interfaccia e modifico il metodo loadUserByUsername
Ora bisogna ancora implemetare l'interfaccia ApplicationUserDao nella quale
inserire la logica per selezionare l'utente dal db
 */

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserDao applicationUserDao;

    public ApplicationUserService(ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDao
                 .selectApplicationUserByUserName(username)
                 .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found.", username)));
    }
}
