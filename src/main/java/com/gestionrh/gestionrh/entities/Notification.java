package com.gestionrh.gestionrh.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="message should not be null")
    private String message;
    @NotNull(message = "read-status cannot be null")
    @Column(name = "is_read")
    private  boolean isRead=false;
    @NotNull(message = "Date is required")
    private LocalDateTime createAt = LocalDateTime.now();

    @ManyToOne
    private Employee employee;
}
