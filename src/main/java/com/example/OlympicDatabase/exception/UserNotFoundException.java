package com.example.OlympicDatabase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The User Not Found")
public class UserNotFoundException extends RuntimeException {
}
