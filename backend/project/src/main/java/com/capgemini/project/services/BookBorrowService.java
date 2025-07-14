package com.capgemini.project.services;

import java.util.List;

import com.capgemini.project.entities.BookBorrow;

public interface BookBorrowService {

    BookBorrow getBorrowById(Long id);

    BookBorrow updateBookBorrow(Long id, BookBorrow bookBorrow);

    BookBorrow patchBookBorrow(Long id, BookBorrow bookBorrow);

    void deleteBookBorrow(Long id);

    List<BookBorrow> getAllBorrows();

    List<BookBorrow> findByUserId(Long userId);

    BookBorrow createBookBorrow(BookBorrow bookBorrow);
}
