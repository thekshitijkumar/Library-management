package com.spring.Librarymanagement.repository;

import com.spring.Librarymanagement.models.Book;
import com.spring.Librarymanagement.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("select b from Book b where b.available=:availability")
    public List<Book> findBooksByAvailability(boolean availability);


    @Query("select b from Book b where b.available=:availability and b.genre=:genre")
    List<Book> findBooksByGenreAndAvailability(String genre, boolean availability);

    @Query("select b from Book b where b.author in (select a from Author a where a.name=:author_name) and b.available=:availability")
    List<Book> findBooksByAuthorAndAvailability(String author_name, boolean availability);

    @Query("select b from Book b where b.available=:availability and b.genre=:genre and b.author in (select a from Author a where a.name=:author_name)")
    List<Book> findBooks(String author_name, String genre, boolean availability);

    @Modifying
    @Query("update Book b set b.card=:#{#book.card} , b.available=:#{#book.available} where b.id=:#{#book.id}")
    void updateBook(Book book);


}
