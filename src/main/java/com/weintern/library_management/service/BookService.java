package com.weintern.library_management.service;

import com.weintern.library_management.models.Book;
import com.weintern.library_management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class BookService {
    private final BookRepository bookRepository;

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getABook(Long id){
        return bookRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Not Found"));

    }

    public void addBook(Book book){
        bookRepository.save(book);
    }
    public Book updateBook(Long id, Book bookDetails) {
        Book existingBook = getABook(id);

        existingBook.setIsbn(bookDetails.getIsbn());
        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setPublisher(bookDetails.getPublisher());
        existingBook.setCategory(bookDetails.getCategory());
        existingBook.setLanguage(bookDetails.getLanguage());
        existingBook.setPublicationYear(bookDetails.getPublicationYear());
        existingBook.setTotalCopies(bookDetails.getTotalCopies());
        existingBook.setAvailableCopies(bookDetails.getAvailableCopies());
        existingBook.setShelfLocation(bookDetails.getShelfLocation());
        existingBook.setStatus(bookDetails.getStatus());
        existingBook.setUpdatedAt(LocalDateTime.now());

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        Book existingBook = getABook(id); // throws if not found
        bookRepository.delete(existingBook);
    }
}
