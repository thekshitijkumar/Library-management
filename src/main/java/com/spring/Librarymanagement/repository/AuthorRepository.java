package com.spring.Librarymanagement.repository;

import com.spring.Librarymanagement.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query("update Author a set a.name = :#{#author.name}, " +
            "a.age = :#{#author.age}, " +
            "a.emailId = :#{#author.emailId} ," +
            "a.country = :#{#author.country} " +
            "where a.id = :#{#author.id}")
    void modifyAuthor(Author author);
}
