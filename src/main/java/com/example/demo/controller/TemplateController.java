package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
Creo il controller che gestirà la pagina login.html
Annoto la classe con @Controller, @RequestMapping e il metodo
con @GetMapping
 */

@Controller
@RequestMapping(value = "/")
public class TemplateController {

    @GetMapping("login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("courses")
    public String getCourses(){
        return "courses";
    }
}
