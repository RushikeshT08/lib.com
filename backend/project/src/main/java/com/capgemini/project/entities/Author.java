package com.capgemini.project.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_id", nullable = false, unique = true)
    private Integer authorId; // Manually entered Author ID

    @Column(name = "author_name", nullable = false)
    private String name;

    @Column(name = "bio", nullable = false)
    private String bio;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    public Author() {
    }

    public Author(Integer authorId, String name, String bio) {
        this.authorId = authorId;
        this.name = name;
        this.bio = bio;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
