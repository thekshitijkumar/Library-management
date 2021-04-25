package com.spring.Librarymanagement.repository;

import com.spring.Librarymanagement.models.Book;
import com.spring.Librarymanagement.models.Transaction;
import com.spring.Librarymanagement.models.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    @Query("select t from Transaction t where t.card.id=:cardId and t.book.id=:bookId and t.transactionStatus=:successful and t.issue=:isIssue")
    public List<Transaction> find(int bookId, int cardId, TransactionStatus successful, boolean isIssue);

    @Query("select t from Transaction t where t.card.id=:cardId")
    List<Transaction> getTransactionsForCard(int cardId);
}
