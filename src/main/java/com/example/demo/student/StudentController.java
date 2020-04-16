package com.example.demo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/*
Creo una classe annotata @RestController e aggiungo @RequestMapping con il quale indico
URL di base per accedere al controller e ai vari metodi definiti all'interno.
Annoto il metodo getStudent con @GetMapping(path = "{studentId}").Associo il valore passato
attraverso URL alla variabile studentID e all'interno del metodo con @PathVariable("studentId")
rendo disponibile tale valore al metodo stesso.
 */

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James BOND"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow( () -> new IllegalStateException("Student " + studentId + " does not exist"));
    }

}
