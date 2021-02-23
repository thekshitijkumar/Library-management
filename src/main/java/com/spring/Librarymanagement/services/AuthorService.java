package com.spring.Librarymanagement.services;

import com.spring.Librarymanagement.models.Author;
import com.spring.Librarymanagement.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public void createAuthor(Author author)
    {
        authorRepository.save(author);
    }

    public Author getAuthor(int id) {
        Author author=authorRepository.findById(id).get();
        return author;
    }

    public void modifyAuthor(Author author) {
        authorRepository.modifyAuthor(author);
    }
}
