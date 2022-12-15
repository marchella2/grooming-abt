package com.example.tugas1_marchellaps.repository;

import com.example.tugas1_marchellaps.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
