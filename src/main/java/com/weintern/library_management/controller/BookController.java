package com.weintern.library_management.controller;

import com.weintern.library_management.models.Book;
import com.weintern.library_management.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
            List<Book> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getABook(@RequestParam Long id){
        Book book = bookService.getABook(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book){
        bookService.addBook(book);
        return new ResponseEntity<>("Added Successfully",HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}


