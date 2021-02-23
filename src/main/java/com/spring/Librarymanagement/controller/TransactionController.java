package com.spring.Librarymanagement.controller;

import com.spring.Librarymanagement.services.TransactionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
