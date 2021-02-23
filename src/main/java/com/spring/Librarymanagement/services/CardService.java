package com.spring.Librarymanagement.services;

import com.spring.Librarymanagement.models.Card;
import com.spring.Librarymanagement.models.CardStatus;
import com.spring.Librarymanagement.models.Student;
import com.spring.Librarymanagement.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private Logger logger= LoggerFactory.getLogger(CardService.class);
    @Autowired
    CardRepository cardRepository;

    public Card createCard(Student student)
    {
        Card card=new Card();
        card.setStudent(student);
        student.setCard(card);
        cardRepository.save(card);
        return card;
    }
    public void deactivateCard(int studentId)
    {
       cardRepository.deactivateCard(studentId, CardStatus.DEACTIVATED.toString());
       logger.info("Card deactivation is successful");

    }
}
