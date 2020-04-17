package com.example.demo.security;

/*
Creo un ENUM per definire i ruoli. Ad un ruolo posso associare 0 o più permessi
Dopo aver creato la ENUM ApplicationUserPermission in questa ENUM aggiungo un set
permissions (ogni ruolo può avere 0 o più permessi), creo il costruttore ed il metodo
getter
Uso Guava e creo un nuovo HashSet vuoto per il ruolo STUDENT (non ha permessi) e
un HashSet con tutti i permessi per il ruolo ADMIN. I permessi vengono presi dalla
ENUM ApplicationUserPermission che importo staticamente
In ApplicationSecurityConfig collego i ruoli appena creati ai rispettivi utenti
 */

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.demo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
