package com.spring.Librarymanagement;

import com.spring.Librarymanagement.models.*;
import com.spring.Librarymanagement.repository.AuthorRepository;
import com.spring.Librarymanagement.repository.BookRepository;
import com.spring.Librarymanagement.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}


	@Autowired
	CardRepository cardRepository;
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	BookRepository bookRepository;
	@Override
	public void run(String... args) throws Exception {
//		Student student=new Student(24,"ABC","India","abc@gmail.com");
//		Card card=new Card();
//		student.setCard(card);
//		card.setStudent(student);
//
//		cardRepository.save(card);
//
//		Author author=new Author("Shakespeare",40,"shakespeare@pigeon.co.in","UK");
//		Book book=new Book("Physics",author, Genre.PHYSICS,true);
//		authorRepository.save(author);	//first need to save author details then only book can be saved
//		bookRepository.save(book);
//		System.out.println(bookRepository.findByName("Physics"));

		System.out.println(authorRepository.findByName("Tim"));




	}
}
