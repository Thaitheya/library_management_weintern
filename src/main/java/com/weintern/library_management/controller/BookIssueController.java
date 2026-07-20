package com.weintern.library_management.controller;

import com.weintern.library_management.models.BookIssue;
import com.weintern.library_management.payload.BookIssueDTO;
import com.weintern.library_management.service.BookIssueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-issue")
public class BookIssueController {

    private final BookIssueService bookIssueService;

    public BookIssueController(BookIssueService bookIssueService) {
        this.bookIssueService = bookIssueService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookIssue>> getAllIssuedBooks() {
        List<BookIssue> issues = bookIssueService.getAllIssuedBooks();
        return ResponseEntity.ok(issues);
    }
    @PostMapping
    public ResponseEntity<BookIssue> issueBook(@RequestBody BookIssueDTO dto) {
        BookIssue issue = bookIssueService.issueBook(dto);
        return new ResponseEntity<>(issue, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookIssue> getIssue(@PathVariable Long id) {
        return ResponseEntity.ok(bookIssueService.getIssueById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookIssue> updateIssue(
            @PathVariable Long id,
            @RequestBody BookIssueDTO dto) {

        BookIssue updatedIssue = bookIssueService.updateIssue(id, dto);
        return ResponseEntity.ok(updatedIssue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable Long id) {
        bookIssueService.deleteIssue(id);
        return ResponseEntity.ok("Book Issue Deleted Successfully");
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<BookIssue> returnBook(@PathVariable Long id) {
        BookIssue issue = bookIssueService.returnBook(id);
        return ResponseEntity.ok(issue);
    }
}