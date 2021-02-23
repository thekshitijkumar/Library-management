package com.spring.Librarymanagement.controller;

import com.spring.Librarymanagement.models.Book;
import com.spring.Librarymanagement.models.Genre;
import com.spring.Librarymanagement.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;
    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book)
    {
        bookService.addBook(book);
        return new ResponseEntity<>("Book is added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/getBooks")
    public ResponseEntity  getBooks(@RequestParam(required = false) String author_name,
                                @RequestParam(required = false) String genre,
                                @RequestParam(required = false,defaultValue = "true") Boolean availability)
    {
        List<Book> bookList = bookService.findBooks(author_name, genre,availability);
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }
}
