package com.spring.Librarymanagement.controller;


import com.spring.Librarymanagement.models.Card;
import com.spring.Librarymanagement.models.Student;
import com.spring.Librarymanagement.services.CardService;
import com.spring.Librarymanagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    CardService cardService;
    @PostMapping("/createStudent")
    public ResponseEntity createStudent(@RequestBody Student student)
    {
        studentService.createStudent(student);
        return new ResponseEntity("Student details are added successfully ", HttpStatus.CREATED);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity updateStudent(@RequestBody Student student)
    {
        studentService.updateStudent(student);
        return new ResponseEntity("Student Details are updated successfully",HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity deleteStudent(@RequestParam int id)
    {
        studentService.deleteStudent(id);
        return new ResponseEntity("Student is deleted successfully",HttpStatus.OK);
    }


}
