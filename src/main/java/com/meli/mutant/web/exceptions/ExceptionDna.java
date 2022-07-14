package com.meli.mutant.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "sadassaddsa")
public class ExceptionDna extends RuntimeException{
    public ExceptionDna(String message) {
        super(message);
    }


}
