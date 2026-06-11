package com.gestionrh.gestionrh.controller;


import com.gestionrh.gestionrh.entities.Conge;
import com.gestionrh.gestionrh.repository.CongeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/conges")
@AllArgsConstructor
public class CongeController {
    private  CongeRepository congeRepository;


    @PostMapping
    public ResponseEntity<Conge> create(@RequestBody Conge conge) {
        conge.setStatus("PENDING");
        conge.setCreateAt(LocalDate.now());
        return ResponseEntity.ok(congeRepository.save(conge));

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
        conge.setStatus("APPROVED");
        return congeRepository.save(conge);
}

    @PutMapping("/{id}/reject")
    public Conge reject(@PathVariable Long id){
        Conge conge=congeRepository.findById(id).orElseThrow();
        conge.setStatus("REJECTED");
        return congeRepository.save(conge);
    }


@GetMapping("/status")
    public ResponseEntity<List<Conge>> status(@RequestParam String status){
         List<Conge> conges=congeRepository.findByStatusIgnoreCase(status);
   if(conges.isEmpty()){
       return ResponseEntity.notFound().build();
   }else{
    return ResponseEntity.ok(conges);
   }
    }







}



