package com.gestionrh.gestionrh.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private  boolean isRead;
    private LocalDate createAt;
    @ManyToOne
    private Employee employee;
}
