package com.gestionrh.gestionrh.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "start Date is required")
    private LocalDate startDate;

    @NotNull(message = "end Date is required")
    private LocalDate endDate;

    @NotBlank(message = "reason is required")
    private String reason;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Employee employee;
}
