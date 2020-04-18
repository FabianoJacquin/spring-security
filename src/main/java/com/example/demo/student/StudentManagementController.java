package com.example.demo.student;

/*
Creo la lista STUDENTS e 4 metodi. Alcuni saranno permessi solo ad ADMIN (write)
e altri anche ad ADMINTRAINEE
I metodi che modificano in dati sono di default protetti in Spring. Bisogna inserire csrf.disable in
ApplicationSecurityConfig
 */

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James BOND"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping
    public List<Student> getAllStudents(){
        System.out.println("getAllStudents");
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("registerNewStudent");
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId,
                              @RequestBody Student student){
        System.out.println("updateStudent");
        System.out.println(String.format("%s %s", studentId, student));
    }

}
