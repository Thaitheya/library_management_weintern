package com.weintern.library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weintern.library_management.models.BookIssue;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long>{
    
}
