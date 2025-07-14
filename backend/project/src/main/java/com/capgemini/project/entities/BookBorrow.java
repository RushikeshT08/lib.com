package com.capgemini.project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class BookBorrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Ensures auto-incremented ID
    private Long id;

    @NotNull(message = "User ID must be provided")
    private Long userId;

    @NotBlank(message = "Book title is mandatory")
    private String bookTitle;

    @NotBlank(message = "Book ID is mandatory")
    private String bookId;

    @NotBlank(message = "Genre is mandatory")
    private String genre;

    @NotNull(message = "Borrow date must be provided")
    private LocalDate borrowDate;

    @NotNull(message = "Return date must be provided")
    private LocalDate returnDate;

    @NotBlank(message = "Status must be provided")
    private String status;

    // Default constructor
    public BookBorrow() {}

    // Constructor for initializing fields
    public BookBorrow(Long userId, String bookTitle, String bookId, String genre,
                      LocalDate borrowDate, LocalDate returnDate, String status) {
        this.userId = userId;
        this.bookTitle = bookTitle;
        this.bookId = bookId;
        this.genre = genre;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookBorrow [id=" + id + ", userId=" + userId + ", bookTitle=" + bookTitle +
                ", bookId=" + bookId + ", genre=" + genre + ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate + ", status=" + status + "]";
    }
}
