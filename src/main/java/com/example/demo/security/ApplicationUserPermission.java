package com.example.demo.security;

/*
Come creare i permessi è soggettivo. Una maniera per farlo è di scrivere RUOLO:PERMESSO
dove PERMESSO può essere READ oppure WRITE
Visto che le singole ENUM contengono una string devo dichiarare la string (che chiamerò
permission e creo il costruttore e il getter)
Devo collegare i permessi ai ruoli e mi serve una nuova dipendenza che inserisco in pom.xml (google
guava). Guava mette a disposizione alcuni metodi che saranno comodi.
I permessi devono essere collegati ai ruoli nella ENUM ApplicationUserRole
 */

public enum ApplicationUserPermission {

    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
