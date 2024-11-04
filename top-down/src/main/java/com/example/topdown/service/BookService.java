package com.example.topdown.service;

import com.example.topdown.model.Book;
import com.example.topdown.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContaining(query);
    }
}