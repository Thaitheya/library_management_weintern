package com.weintern.library_management.controller;

import com.weintern.library_management.models.Book;
import com.weintern.library_management.payload.BookDTO;
import com.weintern.library_management.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getABook(@PathVariable Long id) {
        Book book = bookService.getABook(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Book> addBook(
            @RequestPart("book") BookDTO dto,
            @RequestPart("image") MultipartFile image) throws IOException {

        Book savedBook = bookService.addBook(dto, image);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getBookImage(@PathVariable Long id) {

        Book book = bookService.getABook(id);

        if (book.getImage() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(book.getImage());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @RequestPart("book") BookDTO dto,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        Book updatedBook = bookService.updateBook(id, dto, image);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
