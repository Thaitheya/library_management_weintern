package com.weintern.library_management.service;

import com.weintern.library_management.models.Book;
import com.weintern.library_management.models.BookIssue;
import com.weintern.library_management.models.Member;
import com.weintern.library_management.models.User;
import com.weintern.library_management.payload.BookIssueDTO;
import com.weintern.library_management.repository.BookIssueRepository;
import com.weintern.library_management.repository.BookRepository;
import com.weintern.library_management.repository.MemberRepository;
import com.weintern.library_management.repository.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookIssueService {

    private final BookIssueRepository bookIssueRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    public BookIssueService(
            BookIssueRepository bookIssueRepository,
            BookRepository bookRepository,
            MemberRepository memberRepository,
            UserRepository userRepository) {

        this.bookIssueRepository = bookIssueRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    // Get All
    public List<BookIssue> getAllIssuedBooks() {
        return bookIssueRepository.findAll();
    }

    // Get One
    public BookIssue getIssueById(Long id) {
        return bookIssueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue Record Not Found"));
    }

    // Issue Book
    public BookIssue issueBook(BookIssueDTO dto) {

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member Not Found"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is Out of Stock");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BookIssue issue = new BookIssue();

        issue.setBook(book);
        issue.setMember(member);
        issue.setIssuedBy(user);

        issue.setIssueDate(dto.getIssueDate());
        issue.setDueDate(dto.getDueDate());
        issue.setReturnDate(dto.getReturnDate());

        issue.setFine(dto.getFine());
        issue.setStatus(dto.getStatus());
        issue.setIssueStatus(dto.getIssueStatus());
        issue.setRemarks(dto.getRemarks());

        return bookIssueRepository.save(issue);
    }

    // Update Issue
    public BookIssue updateIssue(Long id, BookIssueDTO dto) {

        BookIssue issue = getIssueById(id);

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member Not Found"));

        User user = userRepository.findById(dto.getIssuedById())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        issue.setBook(book);
        issue.setMember(member);
        issue.setIssuedBy(user);

        issue.setIssueDate(dto.getIssueDate());
        issue.setDueDate(dto.getDueDate());
        issue.setReturnDate(dto.getReturnDate());

        issue.setFine(dto.getFine());
        issue.setStatus(dto.getStatus());
        issue.setIssueStatus(dto.getIssueStatus());
        issue.setRemarks(dto.getRemarks());

        return bookIssueRepository.save(issue);
    }

    // Delete
    public void deleteIssue(Long id) {

        BookIssue issue = getIssueById(id);
        Book book = issue.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        bookIssueRepository.delete(issue);
    }

    public BookIssue returnBook(Long issueId) {

        BookIssue issue = bookIssueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue Record Not Found"));

        if (issue.getIssueStatus().name().equals("RETURNED")) {
            throw new RuntimeException("Book already returned");
        }

        issue.setReturnDate(LocalDate.now());

        Book book = issue.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        long overdueDays = ChronoUnit.DAYS.between(
                issue.getDueDate(),
                issue.getReturnDate());

        if (overdueDays > 0) {
            issue.setFine(BigDecimal.valueOf(overdueDays * 10));
        } else {
            issue.setFine(BigDecimal.ZERO);
        }

        issue.setIssueStatus(com.weintern.library_management.enums.IssueStatus.RETURNED);

        return bookIssueRepository.save(issue);
    }

    
}