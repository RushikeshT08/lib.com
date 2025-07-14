package com.capgemini.project.repositories;

import com.capgemini.project.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByAuthorId(Integer authorId);
    Optional<Author> findByName(String name);
}

