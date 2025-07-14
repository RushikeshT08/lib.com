package com.capgemini.project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Auto-generated primary key

    @NotBlank(message = "Book title is mandatory")
    private String bookTitle;

    @NotBlank(message = "Author Name is mandatory")
    private String author;

    @NotBlank(message = "ISBN is mandatory")
    private String isbn;

    @NotBlank(message = "Book Url is mandatory")
    private String bookUrl;

    @NotBlank(message = "Genre is mandatory")
    private String genre;

    @NotBlank(message = "Description must be provided")
    private String description;

	public Book() {
		super();
	}

	public Book(Long id, @NotBlank(message = "Book title is mandatory") String bookTitle,
			@NotBlank(message = "Author Name is mandatory") String author,
			@NotBlank(message = "ISBN is mandatory") String isbn,
			@NotBlank(message = "Book Url is mandatory") String bookUrl,
			@NotBlank(message = "Genre is mandatory") String genre,
			@NotBlank(message = "Description must be provided") String description) {
		super();
		this.id = id;
		this.bookTitle = bookTitle;
		this.author = author;
		this.isbn = isbn;
		this.bookUrl = bookUrl;
		this.genre = genre;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookTitle=" + bookTitle + ", author=" + author + ", isbn=" + isbn + ", bookUrl="
				+ bookUrl + ", genre=" + genre + ", description=" + description + "]";
	}
    
}
