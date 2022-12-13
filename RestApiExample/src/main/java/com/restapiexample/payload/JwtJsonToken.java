package com.restapiexample.payload;

import lombok.Getter;

@Getter
public class JwtJsonToken {

    private String token;
    private String tokenType="Bearer";

    public JwtJsonToken(String token){
        this.token=token;
    }

}
