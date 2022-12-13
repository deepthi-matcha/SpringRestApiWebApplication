package com.restapiexample.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class PersonNotFound extends RuntimeException{
    private String message;

    public PersonNotFound(String message){
        super(message);
        this.message=message;

    }
}
