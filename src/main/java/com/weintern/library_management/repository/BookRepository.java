package com.weintern.library_management.repository;

import com.weintern.library_management.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
