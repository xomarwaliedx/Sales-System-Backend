package com.example.Sales.System.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RestrictedAccess extends RuntimeException {
    public RestrictedAccess(String message) {
        super(message);
    }
}
