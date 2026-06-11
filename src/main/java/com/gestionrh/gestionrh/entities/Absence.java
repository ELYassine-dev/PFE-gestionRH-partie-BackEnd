package com.gestionrh.gestionrh.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDate date;
    private String reason;

    @ManyToOne
    private Employee employee;
}
