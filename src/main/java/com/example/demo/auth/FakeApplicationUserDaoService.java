package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;

/*
All'interno di questa classe ho bisogno degli stessi utenti che erano stati creati
in ApplicationSecurityConfig. Creo un metodo che mi restituisce gli utenti (ApplicationUser)
e creo gli utenti.
Per quanto riguarda la password, devo utilizzare password encoder (vedi @Bean della classe
PasswordConfig)
Questa classe deve essere annotata con @Repository in quanto rappresenta in db (anche se fake).
Dice a Spring che questa classe deve essere istanziata e che il suo nome è "fake". Se c'è più
di un'istanza dell'imterfaccia ApplicationUserDao in nome serve per segnalare al @Servizio quale
repository utilizzare con @Qualifier
 */

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        STUDENT.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "annasmith",
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(
                        ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "linda",
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(
                        ADMINTRAINEE.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "tom",
                        true,
                        true,
                        true,
                        true)
        );

        return applicationUsers;

    }

}
