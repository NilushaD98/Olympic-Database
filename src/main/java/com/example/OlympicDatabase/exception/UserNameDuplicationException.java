package com.example.OlympicDatabase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "This UserName is Already have an account")
public class UserNameDuplicationException extends RuntimeException{
}
