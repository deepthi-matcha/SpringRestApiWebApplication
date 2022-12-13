package com.restapiexample.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class ApiException extends RuntimeException{
    private String msg;

    public ApiException(String msg){
        super(msg);
        this.msg=msg;
    }
}
