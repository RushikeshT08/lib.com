package com.capgemini.project.services;

import java.util.List;
import com.capgemini.project.entities.Book;

public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book createBook(Book book);

    Book updateBook(Long id, Book book);

    Book patchBook(Long id, Book book);

    void deleteBook(Long id);
}
