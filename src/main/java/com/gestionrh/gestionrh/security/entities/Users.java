package com.gestionrh.gestionrh.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String username;
     private String password;
     private String email;
     private  boolean enabled;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;



}
