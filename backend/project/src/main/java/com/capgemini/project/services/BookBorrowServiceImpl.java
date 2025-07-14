package com.capgemini.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.project.entities.BookBorrow;
import com.capgemini.project.repositories.BookBorrowRepository;

@Service
public class BookBorrowServiceImpl implements BookBorrowService {

    @Autowired
    private BookBorrowRepository borrowRecordRepository;

    @Override
    public BookBorrow getBorrowById(Long id) {
        return borrowRecordRepository.findById(id).orElse(null);
    }

    @Override
    public BookBorrow updateBookBorrow(Long id, BookBorrow borrowRecord) {
        Optional<BookBorrow> existing = borrowRecordRepository.findById(id);
        if (existing.isPresent()) {
            BookBorrow record = existing.get();
            record.setUserId(borrowRecord.getUserId());
            record.setBookId(borrowRecord.getBookId());
            record.setBookTitle(borrowRecord.getBookTitle());
            record.setBorrowDate(borrowRecord.getBorrowDate());
            record.setReturnDate(borrowRecord.getReturnDate());
            record.setStatus(borrowRecord.getStatus());
            return borrowRecordRepository.save(record);
        }
        return null;
    }

    @Override
    public BookBorrow patchBookBorrow(Long id, BookBorrow borrowRecord) {
        // Optional implementation for PATCH
        return updateBookBorrow(id, borrowRecord);
    }

    @Override
    public void deleteBookBorrow(Long id) {
        borrowRecordRepository.deleteById(id);
    }

    @Override
    public List<BookBorrow> getAllBorrows() {
        return borrowRecordRepository.findAll();
    }

    @Override
    public List<BookBorrow> findByUserId(Long userId) {
        return borrowRecordRepository.findByUserId(userId);
    }

    @Override
    public BookBorrow createBookBorrow(BookBorrow borrowRecord) {
        return borrowRecordRepository.save(borrowRecord);
    }
}
