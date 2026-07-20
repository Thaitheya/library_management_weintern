package com.weintern.library_management.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weintern.library_management.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;
    private String category;
    private String language;
    private int publicationYear;
    private int totalCopies;
    private int availableCopies;
    private String shelfLocation;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    List<BookIssue> issues;

}
