package com.example.demo.student;

/*
Ho creato un costruttore con tutti i camppi della classe Student
e i getter (non i setter proprio perchè ho il costruttore)
 */

public class  Student {

    private final Integer studentId;
    private final String studentName;

    public Student(Integer studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
