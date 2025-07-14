package com.capgemini.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.project.entities.BookBorrow;
import com.capgemini.project.repositories.BookBorrowRepository;
import com.capgemini.project.services.BookBorrowService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5501")
@RestController
@RequestMapping("/api/bookborrows")
public class BookBorrowController {

    private final BookBorrowService service;

    @Autowired
    private BookBorrowRepository borrowRecordRepository;

    @Autowired
    public BookBorrowController(BookBorrowService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookBorrow> createBookBorrow(@RequestBody BookBorrow borrowRecord) {
        BookBorrow saved = service.createBookBorrow(borrowRecord);
        return ResponseEntity.created(URI.create("/api/bookborrows/" + saved.getId())).body(saved);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookBorrow>> getBorrowsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookBorrow> getBorrowById(@PathVariable Long id) {
        Optional<BookBorrow> record = borrowRecordRepository.findById(id);
        return record.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BookBorrow>> getAllBorrows() {
        return ResponseEntity.ok(service.getAllBorrows());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookBorrow> updateBorrow(@PathVariable Long id, @RequestBody BookBorrow updatedBorrow) {
        return borrowRecordRepository.findById(id)
            .map(borrow -> {
                borrow.setBookId(updatedBorrow.getBookId());
                borrow.setBookTitle(updatedBorrow.getBookTitle());
                borrow.setBorrowDate(updatedBorrow.getBorrowDate());
                borrow.setReturnDate(updatedBorrow.getReturnDate());
                borrow.setStatus(updatedBorrow.getStatus());
                return ResponseEntity.ok(borrowRecordRepository.save(borrow));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrow(@PathVariable Long id) {
        service.deleteBookBorrow(id);
        return ResponseEntity.noContent().build();
    }
}
