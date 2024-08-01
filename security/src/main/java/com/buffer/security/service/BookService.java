package com.buffer.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buffer.security.model.book.Book;
import com.buffer.security.model.book.BookRepository;
import com.buffer.security.model.book.BookRequest;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {

	@Autowired
    private BookRepository repository;

    public void save(BookRequest request) {
        Book book = new Book();
        book.setId(request.getId());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setCreatedBy(request.getCreateBy());
        Clock cl = Clock.systemUTC(); 
        book.setCreateDate(LocalDateTime.now(cl));
        repository.save(book);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }
}