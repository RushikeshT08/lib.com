package com.capgemini.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.project.entities.BookBorrow;
import com.capgemini.project.entities.Registration;

public interface BookBorrowRepository extends JpaRepository<BookBorrow, Long>{

	List<BookBorrow> findByUserId(Long userId);

}
