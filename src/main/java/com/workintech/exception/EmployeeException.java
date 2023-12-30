package com.workintech.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class EmployeeException extends RuntimeException {
    HttpStatus httpStatus;

    public EmployeeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
