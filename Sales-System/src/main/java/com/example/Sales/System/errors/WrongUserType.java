package com.example.Sales.System.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WrongUserType extends RuntimeException {
    public WrongUserType(String message) {
        super(message);
    }
}
