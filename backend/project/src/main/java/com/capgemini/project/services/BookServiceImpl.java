package com.capgemini.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.project.entities.Book;
import com.capgemini.project.exceptions.BookNotFoundException;
import com.capgemini.project.repositories.BookRepository;

import jakarta.validation.Valid;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Autowired
    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found for ID: " + id));
    }

    @Override
    public Book createBook(@Valid Book book) {
        return repository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found for ID: " + id));

        existing.setBookTitle(updatedBook.getBookTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setIsbn(updatedBook.getIsbn());
        existing.setBookUrl(updatedBook.getBookUrl());
        existing.setGenre(updatedBook.getGenre());
        existing.setDescription(updatedBook.getDescription());

        return repository.save(existing);
    }

    @Override
    public Book patchBook(Long id, Book patch) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found for ID: " + id));

        if (patch.getBookTitle() != null) {
            existing.setBookTitle(patch.getBookTitle());
        }
        if (patch.getAuthor() != null) {
            existing.setAuthor(patch.getAuthor());
        }
        if (patch.getIsbn() != null) {
            existing.setIsbn(patch.getIsbn());
        }
        if (patch.getBookUrl() != null) {
            existing.setBookUrl(patch.getBookUrl());
        }
        if (patch.getGenre() != null) {
            existing.setGenre(patch.getGenre());
        }
        if (patch.getDescription() != null) {
            existing.setDescription(patch.getDescription());
        }

        return repository.save(existing);
    }

    @Override
    public void deleteBook(Long id) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException("Cannot delete. Book not found for ID: " + id);
        }
        repository.deleteById(id);
    }
}
