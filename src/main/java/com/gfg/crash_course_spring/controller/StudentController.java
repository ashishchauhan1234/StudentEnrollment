package com.gfg.crash_course_spring.controller;

import com.gfg.crash_course_spring.model.Student;
import com.gfg.crash_course_spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public String helloStudent() {
        return "Hello World";
    }

    @PostMapping("/student/add")
    public Student addStudent(@RequestBody Student student) {
        System.out.println("student :: " + student);
        return this.studentService.addStudent(student);
//        return new Student();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable("id") long id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/student/{id}")
    public String  deleteStudentById(@PathVariable("id") long id) {
        return studentService.deleteStudentById(id);
    }
    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }
}
