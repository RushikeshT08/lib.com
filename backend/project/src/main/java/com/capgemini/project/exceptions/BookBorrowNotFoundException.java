package com.capgemini.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookBorrowNotFoundException extends RuntimeException {
	public BookBorrowNotFoundException(String message) {
		super(message);
	}
}
