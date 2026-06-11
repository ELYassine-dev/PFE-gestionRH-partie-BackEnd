package com.gestionrh.gestionrh.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String Position;
    private String department;
    private Double salary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;}

//Optional
//   @OneToMany(mappedBy = "employee")
//   private List<Conge> conges;