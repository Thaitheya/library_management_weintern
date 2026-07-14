package com.weintern.library_management.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.weintern.library_management.enums.IssueStatus;
import com.weintern.library_management.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long issue_id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; 
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private BigDecimal fine;
    private Status status;
    private IssueStatus issueStatus;
    private String remarks;

    @ManyToOne
    Book books;

    @ManyToOne
    Member members;

    @ManyToOne
    User issuedBy;
}
