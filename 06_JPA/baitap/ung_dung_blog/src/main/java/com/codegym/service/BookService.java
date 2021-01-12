package com.codegym.service;

import com.codegym.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Page<Book> getAll(Pageable pageable);

    Book findById(Long bookId);
    void update(Book bookDetails);
    void delete(Long bookId);

   void create(Book bookDetails);
    Page<Book> findAllByAuthorContaining(Pageable pageable, String author);
}
