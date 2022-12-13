package com.restapiexample.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
