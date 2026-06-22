package com.gestionrh.gestionrh.repository;

import com.gestionrh.gestionrh.entities.Absence;
import com.gestionrh.gestionrh.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    List<Absence> findByEmployeeId(Long id);
    List<Absence> findByStatus(Status status);
    void deleteByEmployeeId(Long id);
}
