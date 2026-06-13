package com.gestionrh.gestionrh.controller;

import com.gestionrh.gestionrh.entities.Employee;
import com.gestionrh.gestionrh.repository.EmployeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Transactional
@AllArgsConstructor
public class EmployeeController {

    private EmployeRepository empRepository;


    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> emp=empRepository.findAll();
        if(emp==null){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok().body(emp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

        Employee emp= empRepository.findById(id).get();
        if(emp==null){
            return ResponseEntity.notFound().build();
        }else return ResponseEntity.ok().body(emp);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        if(employee==null){
            return ResponseEntity.notFound().build();
                    }else{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(empRepository.save(employee));
        }
    }

    @DeleteMapping("{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
                  empRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateAll(@PathVariable Long id,
                                             @RequestBody Employee employee){

        Optional<Employee> emp=empRepository.findById(id);
        if(emp.isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(empRepository.save(employee));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }



    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateOne(@PathVariable Long id,
                                              @RequestBody Employee employee){
        Employee emp=empRepository.findById(id).get();
        if(emp!=null){
            if(employee.getFirstName()!=null){
                emp.setFirstName(employee.getFirstName());
            }
            if(employee.getLastName()!=null){
                emp.setLastName(employee.getLastName());
            }
            if(employee.getEmail()!=null){
                emp.setEmail(employee.getEmail());
            }
            if(employee.getPhone()!=null){
                emp.setPhone(employee.getPhone());
            }
            if(employee.getDepartment()!=null){
                emp.setDepartment(employee.getDepartment());
            }
            if(employee.getPosition()!=null){
                emp.setPosition(employee.getPosition());
            }
            if(employee.getSalary()!=0){
                emp.setSalary(employee.getSalary());
            }
            if(employee.getHireDate()!=null){
                emp.setHireDate(employee.getHireDate());
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(empRepository.save(emp));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

 @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam String keyword){
        List<Employee> emp=empRepository.searchByFullName(keyword);
        if(emp.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(emp);
        }

 }




}
