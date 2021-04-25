package com.spring.Librarymanagement.controller;


import com.spring.Librarymanagement.models.Card;
import com.spring.Librarymanagement.models.Student;
import com.spring.Librarymanagement.security.SecurityConfig;
import com.spring.Librarymanagement.services.CardService;
import com.spring.Librarymanagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    CardService cardService;

    @GetMapping("/all")
    public ResponseEntity getAllStudents()
    {
        return new ResponseEntity<>("List of all student is "+null,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity getStudentDetails(int studentId)
    {

        Student student=studentService.findStudentById(studentId);
        return new ResponseEntity<>("The Student details are as follows "+student,HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity createStudent(@RequestBody Student student)
    {
        studentService.createStudent(student);
        return new ResponseEntity("Student details are added successfully ", HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity updateStudent(@RequestBody Student student)
    {
        studentService.updateStudent(student);
        return new ResponseEntity("Student Details are updated successfully",HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity deleteStudent(@RequestParam int id)
    {
        studentService.deleteStudent(id);
        return new ResponseEntity("Student is deleted successfully",HttpStatus.OK);
    }


}
