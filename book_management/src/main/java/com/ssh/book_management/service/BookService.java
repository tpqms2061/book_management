package com.ssh.book_management.service;


import com.ssh.book_management.model.Book;
import com.ssh.book_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("책없음"));
        //에러처리 null값에 대한
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Integer id, Book book) {
        getById(id);
        book.setId(id);
        return bookRepository.save(book);
    }



    public void delete(Integer id) {
        bookRepository.delete(id);
    }
}
