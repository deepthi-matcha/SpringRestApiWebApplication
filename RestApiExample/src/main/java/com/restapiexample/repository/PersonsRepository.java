package com.restapiexample.repository;

import com.restapiexample.entity.Persons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonsRepository extends JpaRepository<Persons,String> {

    <Optional>Persons findByEmail(String email);
}
