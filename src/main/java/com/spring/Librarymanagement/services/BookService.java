package com.spring.Librarymanagement.services;

import com.spring.Librarymanagement.models.Book;
import com.spring.Librarymanagement.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private Logger logger = LoggerFactory.getLogger(BookService.class);
    @Autowired
    BookRepository bookRepository;
    public void addBook(Book book)
    {
        bookRepository.save(book);
        logger.info("Book {} is added successfully",book);
    }


    public List<Book> findBooks(String author_name, String genre, Boolean availability) {
        if(author_name==null && genre==null)
        {
            return bookRepository.findBooksByAvailability(availability);
        }
        else if(author_name==null)//book not null
        {
            return bookRepository.findBooksByGenreAndAvailability(genre,availability);
        }
        else if(genre==null)
        {
            return bookRepository.findBooksByAuthorAndAvailability(author_name,availability);
        }
        else//all exists
        {
            return bookRepository.findBooks(author_name,genre,availability);
        }

    }
}
