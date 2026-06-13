package com.gestionrh.gestionrh.controller;

import com.gestionrh.gestionrh.entities.Absence;
import com.gestionrh.gestionrh.entities.Employee;
import com.gestionrh.gestionrh.entities.Status;
import com.gestionrh.gestionrh.repository.AbsenceRepository;
import com.gestionrh.gestionrh.repository.EmployeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/absences")
@AllArgsConstructor
public class AbsenceController {
    private AbsenceRepository absenceRepository;
    private EmployeRepository employeRepository;


    @GetMapping
    public ResponseEntity<List<Absence>> getAllAbsences(){
        List<Absence> absences = absenceRepository.findAll();
        if(absences.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(absences);
        }else {
            return ResponseEntity.ok(absences);
        }    }

    @PostMapping
    public ResponseEntity<Absence> createAbsence(@RequestBody Absence absence){
        Optional<Employee> emp=employeRepository.findById(absence.getEmployee().getId());
        if(emp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                    }else{
            absence.setStatus(Status.ABSENT);
            return ResponseEntity.ok(absenceRepository.save(absence));
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<Absence>> getEmployeeAbsences(@PathVariable Long id){
        List<Absence> absence=absenceRepository.findByEmployeeId(id);
        if(absence==null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(absence);
        }

    }
    @PutMapping("/{id}/justify")
    public Absence absenceJustify(@PathVariable Long id){
        Absence absence1=absenceRepository.findById(id).orElseThrow();
        absence1.setStatus(Status.JUSTIFIED);
        return absenceRepository.save(absence1);
    }
    @PutMapping("/{id}/reject")
    public Absence absenceReject(@PathVariable Long id){
        Absence absence1=absenceRepository.findById(id).orElseThrow();
        absence1.setStatus(Status.UNJUSTIFIED);
        return absenceRepository.save(absence1);
    }


    @GetMapping("/status")
    public ResponseEntity<List<Absence>> getAbsenceStatus(@RequestParam Status status){
        List<Absence> absences=absenceRepository.findByStatus(status);
        if (absences.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.ok(absences);
        }

    }




}
