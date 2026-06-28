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
    @NotNull(message = "La date d'embauche est obligatoire")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;
    @NotBlank(message="cin should not be null")
    private String cin;
    @NotBlank(message="adresse should not be null")
    private String adresse;
    @NotNull(message="age should not be null")
    private Integer age;
    @NotBlank(message="diplome should not be null")
    private String diplome;
    @NotBlank(message="gender should not be null")
    private String gender;
    @NotBlank(message="situation familiar should not be null")
    private String situationfamiliar;
    @NotNull(message="experience should not be null")
    private Integer experience;









}

//Optional
//   @OneToMany(mappedBy = "employee")
//   private List<Conge> conges;