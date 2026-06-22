package com.gestionrh.gestionrh.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "start Date is required")
    private LocalDate startDate;
    @NotNull(message = "end Date is required")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "Date de creation is required")
    private LocalDate createAt;

    @NotBlank(message = "reasoon is required")
    private String reason;

    @ManyToOne
    private Employee employee;

}
