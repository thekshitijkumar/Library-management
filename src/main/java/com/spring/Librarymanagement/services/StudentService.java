package com.spring.Librarymanagement.services;


import com.spring.Librarymanagement.models.Card;
import com.spring.Librarymanagement.models.Student;
import com.spring.Librarymanagement.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private Logger logger= LoggerFactory.getLogger(StudentService.class);
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CardService cardService;

    public void createStudent(Student student)
    {
        Card card=cardService.createCard(student);
        logger.info("Student {} is created with card {},student,card");
    }
    public void updateStudent(Student student)
    {
        studentRepository.updateStudentDetails(student);
        logger.info("Student details are updated successfully");
    }
    public void deleteStudent(int id)
    {
        cardService.deactivateCard(id);
        studentRepository.deleteById(id);
        logger.info("student with id {} is deleted",id);
    }

    public Student getDetails(String emailId) {
        return studentRepository.findByEmailId(emailId);
    }

    public Student getDetailsById(int id) {
        return studentRepository.findById(id).get();
    }
}
