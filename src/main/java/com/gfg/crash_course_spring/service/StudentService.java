package com.gfg.crash_course_spring.service;

import com.gfg.crash_course_spring.model.Student;
import com.gfg.crash_course_spring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    public final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        Student addedStudent = studentRepository.save(student);
        System.out.println("Added student :: " + addedStudent);
        return addedStudent;
    }

    public Student getStudentById(long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(null);
    }

    public String deleteStudentById(long id) {
        studentRepository.deleteById(id);
        if (studentRepository.findById(id).isPresent())
            return "Student with id " + id + " is not Deleted";
        else
            return "Student with id " + id + " is Deleted";
    }

    public Student updateStudent(long id, Student student) {
        if (studentRepository.findById(id).isPresent()) {
            Student updatedStudent = studentRepository.findById(id).get();
            updatedStudent.setFirstName(student.getFirstName());
            updatedStudent.setLastName(student.getLastName());
            return studentRepository.save(updatedStudent);
        } else {
            return null;
        }
    }
}
