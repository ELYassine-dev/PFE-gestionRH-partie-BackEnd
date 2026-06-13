package com.gestionrh.gestionrh.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Employee employee;
}
