package com.restapiexample.controller;

import com.restapiexample.entity.Persons;
import com.restapiexample.payload.JwtJsonToken;
import com.restapiexample.payload.LoginDto;
import com.restapiexample.payload.PersonDto;
import com.restapiexample.security.JwtTokenProvider;
import com.restapiexample.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class PersonsController {

    @Autowired
    private PersonsService personsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    //add user to DB
    @PostMapping("/register")
    public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto personDto){
        return new ResponseEntity<>(personsService.addPerson(personDto), HttpStatus.CREATED);


    }

    @PostMapping("/login") public ResponseEntity<JwtJsonToken> loginUser(@RequestBody  LoginDto loginDto){
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPhonenumber()));
        SecurityContextHolder .getContext().setAuthentication(authentication);

        String token=jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtJsonToken(token));

    }


}
