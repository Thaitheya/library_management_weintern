package com.weintern.library_management.service;

import com.weintern.library_management.models.Book;
import com.weintern.library_management.payload.BookDTO;
import com.weintern.library_management.repository.BookRepository;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getABook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not Found"));

    }

    public Book addBook(BookDTO dto, MultipartFile image) throws IOException {

        Book book = new Book();

        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setCategory(dto.getCategory());
        book.setLanguage(dto.getLanguage());
        book.setPublicationYear(dto.getPublicationYear());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());
        book.setShelfLocation(dto.getShelfLocation());
        book.setStatus(dto.getStatus());

        book.setImage(image.getBytes());

        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());

        return bookRepository.save(book);
    }

    public Book updateBook(
            Long id,
            BookDTO dto,
            MultipartFile image) throws IOException {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setCategory(dto.getCategory());
        book.setLanguage(dto.getLanguage());

        book.setPublicationYear(dto.getPublicationYear());

        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());

        book.setShelfLocation(dto.getShelfLocation());
        book.setStatus(dto.getStatus());

        // Update image only if user uploads new image
        if (image != null && !image.isEmpty()) {

            book.setImage(image.getBytes());

        }

        book.setUpdatedAt(LocalDateTime.now());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book existingBook = getABook(id); // throws if not found
        bookRepository.delete(existingBook);
    }

}
