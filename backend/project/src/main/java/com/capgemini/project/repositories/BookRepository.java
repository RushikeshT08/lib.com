package com.capgemini.project.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.project.entities.Book;

public interface BookRepository extends JpaRepository<Book,Long>{

}
