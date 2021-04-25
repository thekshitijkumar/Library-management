package com.spring.Librarymanagement.controller;


import com.spring.Librarymanagement.models.Card;
import com.spring.Librarymanagement.models.Student;
import com.spring.Librarymanagement.security.AuthorityConstants;
import com.spring.Librarymanagement.security.SecurityConfig;
import com.spring.Librarymanagement.security.User;
import com.spring.Librarymanagement.security.UserRepository;
import com.spring.Librarymanagement.services.CardService;
import com.spring.Librarymanagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    CardService cardService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity getAllStudents()
    {
        return new ResponseEntity<>("List of all student is "+null,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity getStudentDetails()
    {
        User principal=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name=principal.getUsername();
        Student student=studentService.getDetails(name);
        return new ResponseEntity<>("The Student details are as follows "+student,HttpStatus.OK);
    }

    @GetMapping("/studentById")
    public ResponseEntity getStudentById(@RequestParam("id") int id)
    {
        Student student=studentService.getDetailsById(id);
        return new ResponseEntity<>("The Student details are as follows "+student,HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity createStudent(@RequestBody Student student)
    {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        studentService.createStudent(student);
        User user= User.builder()
                .username(student.getEmailId())
                .authority(AuthorityConstants.STUDENT_AUTHORITY)
                .password(encoder.encode("pass1234"))
                .build();
        userRepository.save(user);
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

    @PutMapping("/update_password")
    public ResponseEntity updatePassword(@RequestParam("new_password")String password)
    {
        User principal=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name=principal.getUsername();
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String newEncodedPassword=encoder.encode(password);
//        userRepository.updatePassword()
        return null;
    }

}
