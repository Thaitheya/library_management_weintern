package com.weintern.library_management.payload;

import com.weintern.library_management.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

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
    private String image;

}
