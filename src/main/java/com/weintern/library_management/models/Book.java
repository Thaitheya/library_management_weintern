package com.weintern.library_management.models;

import java.time.LocalDateTime;
import java.util.List;
import com.weintern.library_management.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String category;
    private String language;
    private int publicationYear;
    private int totalCopies;
    private int availableCopies;
    private String shelfLocation;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "book")
    List<BookIssue> issues;

}
