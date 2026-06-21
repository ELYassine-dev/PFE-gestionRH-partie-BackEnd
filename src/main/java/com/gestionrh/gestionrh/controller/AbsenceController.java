package com.gestionrh.gestionrh.controller;

import com.gestionrh.gestionrh.entities.Absence;
import com.gestionrh.gestionrh.entities.Employee;
import com.gestionrh.gestionrh.entities.Notification;
import com.gestionrh.gestionrh.entities.Status;
import com.gestionrh.gestionrh.repository.AbsenceRepository;
import com.gestionrh.gestionrh.repository.EmployeRepository;
import com.gestionrh.gestionrh.service.NotificationService;
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
@CrossOrigin(origins = "http://localhost:4200")

public class AbsenceController {
    private AbsenceRepository absenceRepository;
    private EmployeRepository employeRepository;
    private NotificationService notificationService;


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
            Absence a1=absenceRepository.save(absence);
            notificationService.sendNotification(absence.getEmployee(),
                    "Une absence a été enregistrée"  );
            return ResponseEntity.ok(a1);
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
    @PutMapping("/{id}/justifier")
    public Absence absenceJustify(@PathVariable Long id){
        Absence absence1=absenceRepository.findById(id).orElseThrow();
        absence1.setStatus(Status.JUSTIFIED);
        Absence a1=absenceRepository.save(absence1);
        notificationService.sendNotification(absence1.getEmployee(),
                "Votre absence a été justifiée"  );
        return a1;
    }
    @PutMapping("/{id}/unjustifier")
    public Absence absenceReject(@PathVariable Long id){
        Absence absence1=absenceRepository.findById(id).orElseThrow();
        absence1.setStatus(Status.UNJUSTIFIED);
        Absence a1=absenceRepository.save(absence1);
        notificationService.sendNotification(absence1.getEmployee(),
                "La justification de votre absence a été refusée"  );
        return a1;
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
