package com.spring.Librarymanagement.services;

import com.spring.Librarymanagement.models.*;
import com.spring.Librarymanagement.repository.BookRepository;
import com.spring.Librarymanagement.repository.CardRepository;
import com.spring.Librarymanagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {


    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CardRepository cardRepository;

    @Value("${books.max_allowed_books}")
    int max_allowed_books;

    @Value("${books.max_issue_days}")
    int max_allowed_days;
    @Value("${books.fine_per_day}")
    int fine_each_day;

    public String issueBook(int bookId, int cardId) throws Exception {
        //make book as unavailable
        // link with card
        //add entry in transaction table
        Book book=bookRepository.findById(bookId).get();
        Card card=cardRepository.findById(cardId).get();

        Transaction transaction=new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssue(true);

        if(book==null||!book.getAvailable())//if book is null or already issues
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Error while issuing book");
        }
        if(card==null||card.getCardStatus().equals(CardStatus.DEACTIVATED))
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Error while issuing book");
        }
        if(card.getBooks().size()>max_allowed_books)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Error while issuing book");
        }
        //positive case
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        book.setCard(card);
        book.setAvailable(false);
        List<Book> bookList=card.getBooks();
        bookList.add(book);
        card.setBooks(bookList);

        bookRepository.updateBook(book);
        transactionRepository.save(transaction);



        return transaction.getTransactionId();
    }

    public String returnBook(int bookId, int cardId) {
        List<Transaction> transactions=transactionRepository.find(bookId,cardId,TransactionStatus.SUCCESSFUL,true);
        Transaction transaction=transactions.get(transactions.size()-1);

        //calculation of fine
        Date issueDate=transaction.getTransactionDate();    //find issue date
        long issueTime=Math.abs(System.currentTimeMillis()- issueDate.getTime());
        long no_of_days= TimeUnit.DAYS.convert(issueTime,TimeUnit.MILLISECONDS);
        int fine=0;
        if(no_of_days>max_allowed_days)
        {
            fine=(int)(no_of_days-max_allowed_days)*fine_each_day;
        }
        Book book=transaction.getBook();
        book.setAvailable(true);
        book.setCard(null);
        bookRepository.updateBook(book);

        Transaction tr=new Transaction();
        tr.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        tr.setIssue(false);
        tr.setFineAmount(fine);
        tr.setBook(book);
        tr.setCard(transaction.getCard());
        transactionRepository.save(tr);

        return tr.getTransactionId();
    }
}
