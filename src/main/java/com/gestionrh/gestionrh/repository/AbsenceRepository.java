package com.gestionrh.gestionrh.repository;

import com.gestionrh.gestionrh.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
}
