package com.gestionrh.gestionrh.controller;


import com.gestionrh.gestionrh.entities.Conge;
import com.gestionrh.gestionrh.entities.Employee;
import com.gestionrh.gestionrh.entities.Status;
import com.gestionrh.gestionrh.repository.CongeRepository;
import com.gestionrh.gestionrh.repository.EmployeRepository;
import com.gestionrh.gestionrh.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conges")
@AllArgsConstructor
public class CongeController {
    private  CongeRepository congeRepository;
    private EmployeRepository employeRepository;
    private NotificationService notificationService;


    @PostMapping
    public ResponseEntity<Conge> createConge(@RequestBody Conge conge) {
        Optional<Employee> emp=employeRepository.findById(conge.getEmployee().getId());
        if(emp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            conge.setStatus(Status.PENDING);
            conge.setCreateAt(LocalDate.now());
                Conge saved =congeRepository.save(conge);
            notificationService.sendNotification(conge.getEmployee(),
                    "votre demande de conge est envoye" );
            return ResponseEntity.ok(saved);
    }
}

    @GetMapping
    public ResponseEntity<List<Conge>> getAllConge(){
        return ResponseEntity.ok(congeRepository.findAll());
    }


    //@GetMapping("/conges/{id}/employee")
    //"donne tous les congés d’un employé spécifique"

@GetMapping("/employee/{id}")
public List<Conge> getEmployeeConges(@PathVariable Long id){
//"donne-moi tous les congés où employee.id = id"
    return congeRepository.findByEmployeeId(id);
}



@PutMapping("/{id}/approve")
    public Conge approve(@PathVariable Long id){
        Conge conge=congeRepository.findById(id).orElseThrow();
        conge.setStatus(Status.APPROVED);
        Conge saved= congeRepository.save(conge);
    notificationService.sendNotification(
            conge.getEmployee(),
            "Votre demande de congé a été approuvée"
    );
    return saved;
}

    @PutMapping("/{id}/reject")
    public Conge reject(@PathVariable Long id){
        Conge conge=congeRepository.findById(id).orElseThrow();
        conge.setStatus(Status.REJECTED);
        Conge saved= congeRepository.save(conge);
        notificationService.sendNotification(
                conge.getEmployee(),
                "Votre demande de congé a été refusée."
        );
        return saved;
    }


@GetMapping("/status")
    public ResponseEntity<List<Conge>> status(@RequestParam Status status){
         List<Conge> conges=congeRepository.findByStatus(status);
   if(conges.isEmpty()){
       return ResponseEntity.notFound().build();
   }else{
    return ResponseEntity.ok(conges);
   }
    }







}



