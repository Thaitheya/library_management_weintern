package com.weintern.library_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weintern.library_management.models.Book;
import com.weintern.library_management.models.BookIssue;
import com.weintern.library_management.repository.BookIssueRepository;
import com.weintern.library_management.repository.BookRepository;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final BookIssueRepository bookIssueRepository;
    private final BookRepository bookRepository;
    
    public ReportController(BookIssueRepository bookIssueRepository, BookRepository bookRepository) {
        this.bookIssueRepository = bookIssueRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/issued-books")
    public ResponseEntity<List<BookIssue>> issuedBooksReport() {
        return ResponseEntity.ok(bookIssueRepository.findAll());
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<Book>> inventoryReport() {
        return ResponseEntity.ok(bookRepository.findAll());
    }
}
