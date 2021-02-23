package com.spring.Librarymanagement.repository;

import com.spring.Librarymanagement.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface CardRepository extends JpaRepository<Card,Integer> {
    @Modifying
    @Query("update Card c set c.cardStatus =:cardStatus  where c.id in (select s.card from Student s where s.card =:studentId)")
    public void deactivateCard(int studentId,String cardStatus);
}
