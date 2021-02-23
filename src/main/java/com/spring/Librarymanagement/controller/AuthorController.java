package com.spring.Librarymanagement.controller;

import com.spring.Librarymanagement.models.Author;
import com.spring.Librarymanagement.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;
    @PostMapping("/createAuthor")
    public ResponseEntity createAuthor(@RequestBody Author author)
    {
        authorService.createAuthor(author);
        return new ResponseEntity("Author is created successfully", HttpStatus.CREATED);
    }
    @GetMapping("/getAuthor")
    public ResponseEntity getAuthor(@RequestParam int id)
    {
        Author author=authorService.getAuthor(id);
        return new ResponseEntity(author,HttpStatus.OK);
    }
    @PutMapping("/modifyAuthor")
    public ResponseEntity modifyAuthor(@RequestBody Author author)
    {
        authorService.modifyAuthor(author);
        return new ResponseEntity("Author is modified successfully",HttpStatus.OK);
    }
}
