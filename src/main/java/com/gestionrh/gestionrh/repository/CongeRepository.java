package com.gestionrh.gestionrh.repository;

import com.gestionrh.gestionrh.entities.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CongeRepository extends JpaRepository<Conge,Long> {
    List<Conge> findByEmployeeId(Long id);
    List<Conge> findByStatusIgnoreCase(String status);
}
