package com.spring.Librarymanagement.controller;

import com.spring.Librarymanagement.models.Transaction;
import com.spring.Librarymanagement.services.TransactionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity issueBook(@RequestParam("bookId") int bookId,@RequestParam("cardId") int cardId) throws Exception {
        String transactionId=transactionService.issueBook(bookId,cardId);
        return new ResponseEntity("Book is issued successfully with transationId "+transactionId, HttpStatus.OK);
    }
    @PostMapping("/returnBook")
    public ResponseEntity returnBook(@RequestParam("bookId") int bookId,@RequestParam("cardId") int cardId)
    {
        String transactionId=transactionService.returnBook(bookId,cardId);
        return new ResponseEntity("Book is returned successfully with transationId "+transactionId, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity getTransactionsForCard(@RequestParam("cardId") int cardId)
    {
        List<Transaction> transactions=transactionService.getTransactionsForCard(cardId);
        return new ResponseEntity<>("List of Transactions done on card are  "+transactions, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity getTransactions()
    {
        return new ResponseEntity<>("List of Transactions done on card are  "+null, HttpStatus.OK);
    }
}
