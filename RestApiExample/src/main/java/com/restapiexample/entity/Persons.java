package com.restapiexample.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="persons",uniqueConstraints ={@UniqueConstraint(columnNames = {"email"})} )
public class Persons   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="firstName",nullable = false)
    private String firstName;
    @Column(name="lastName",nullable = false)
    private String lastName;
    @Column(name="Email",nullable = false)
    private String Email;
    @Column(name="phoneNumber",nullable = false)
    private String phoneNumber;




}
