package com.spring.Librarymanagement;

import com.spring.Librarymanagement.models.*;
import com.spring.Librarymanagement.repository.AuthorRepository;
import com.spring.Librarymanagement.repository.BookRepository;
import com.spring.Librarymanagement.repository.CardRepository;
import com.spring.Librarymanagement.security.AuthorityConstants;
import com.spring.Librarymanagement.security.User;
import com.spring.Librarymanagement.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

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
	@Autowired
	UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {

//		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
//		User user1= User.builder()
//				.username("John")
//				.password(encoder.encode("john123"))
//				.authority(AuthorityConstants.ADMIN_AUTHORITY)
//				.build();
//
//		User user2= User.builder()
//				.username("Jim")
//				.password(encoder.encode("jim123"))
//				.authority(AuthorityConstants.STUDENT_AUTHORITY)
//				.build();
//
//		userRepository.saveAll(Arrays.asList(user1));
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
