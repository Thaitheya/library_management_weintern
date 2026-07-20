package com.weintern.library_management.payload;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.weintern.library_management.enums.IssueStatus;
import com.weintern.library_management.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookIssueDTO {

    private Long bookId;

    private Long memberId;

    private Long issuedById;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private BigDecimal fine;

    private Status status;

    private IssueStatus issueStatus;

    private String remarks;
}