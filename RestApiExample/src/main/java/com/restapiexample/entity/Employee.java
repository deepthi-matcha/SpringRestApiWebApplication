package com.restapiexample.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="Employee_TBL")
public class Employee   {
    private  String Title;
    private  String Date;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ManagerId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Persons persons;
}
