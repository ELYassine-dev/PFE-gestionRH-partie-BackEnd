package com.gestionrh.gestionrh.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="first name should not be null")
    private String firstName;
    @NotBlank(message="last name should not be null")
    private String lastName;
    @NotNull(message = "Phone number cannot be null")
    private String phone;
    @NotBlank(message = "Invalid email format for warranty")
    private String email;
    @NotBlank(message="position should not be null")
    private String position;
    @NotBlank(message="department should not be null")
    private String department;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    private Double salary;
    @NotBlank(message="hire date should not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;}

//Optional
//   @OneToMany(mappedBy = "employee")
//   private List<Conge> conges;