package com.restapiexample.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class EmployeeNotFound extends  RuntimeException{

    private String msg;
    public EmployeeNotFound(String msg){
        super(msg);
        this.msg=msg;
    }

}
