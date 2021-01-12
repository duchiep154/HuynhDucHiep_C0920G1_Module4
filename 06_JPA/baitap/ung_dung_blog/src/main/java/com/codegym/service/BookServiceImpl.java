package com.codegym.service;

import com.codegym.entity.Book;
import com.codegym.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {


    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> getAll(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(Long bookId) {
        return this.bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public void update(Book bookDetails) {
        this.bookRepository.save(bookDetails);

    }

    @Override
    public void delete(Long bookId) {
        this.bookRepository.deleteById(bookId);

    }

    @Override
    public void create(Book bookDetails) {
         this.bookRepository.save(bookDetails);
    }

    @Override
    public Page<Book> findAllByAuthorContaining(Pageable pageable, String author) {
        return this.bookRepository.findAllByAuthorContaining(pageable, author);
    }
}
