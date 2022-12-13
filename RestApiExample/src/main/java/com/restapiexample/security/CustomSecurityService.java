package com.restapiexample.security;

import com.restapiexample.Exception.PersonNotFound;
import com.restapiexample.entity.Persons;
import com.restapiexample.repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomSecurityService implements UserDetailsService {

    @Autowired
    private PersonsRepository personsRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Persons persons=personsRepository.findByEmail(email);
        Set<String> roles=new HashSet<String>();
        roles.add("ROLE_ADMIN");
        return new User(persons.getEmail(),persons.getPhoneNumber(),userAuthorities(roles));
    }

    private Collection<? extends  GrantedAuthority> userAuthorities(Set<String> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }
}
