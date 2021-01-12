package com.codegym.repository;

import com.codegym.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByAuthorContaining(Pageable pageable,String author);
}
