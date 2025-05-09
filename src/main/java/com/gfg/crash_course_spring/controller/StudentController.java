package com.gfg.crash_course_spring.controller;

import com.gfg.crash_course_spring.exception.NotFoundExceptions;
import com.gfg.crash_course_spring.model.Student;
import com.gfg.crash_course_spring.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student) {
        try {
            return ResponseEntity.ok(this.studentService.addStudent(student));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }

    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") long id) {

        try {
            Student student = studentService.getStudentById(id);
            return ResponseEntity.ok(student);
        } catch (NotFoundExceptions e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(studentService.deleteStudentById(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        try {
            if (id != student.getId()) return ResponseEntity.badRequest().body(Map.of("message", "Id mismatch"));
            Student updateStudent = studentService.updateStudent(id, student);
            return ResponseEntity.ok(updateStudent);
        } catch (NotFoundExceptions e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }
    }
}
