package com.gestionrh.gestionrh.controller;

import com.gestionrh.gestionrh.entities.Employee;
import com.gestionrh.gestionrh.repository.AbsenceRepository;
import com.gestionrh.gestionrh.repository.CongeRepository;
import com.gestionrh.gestionrh.repository.EmployeRepository;
import com.gestionrh.gestionrh.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private EmployeRepository empRepository;
    private CongeRepository congeRepository;
    private NotificationRepository notificationRepository;
    private AbsenceRepository absRepository;


    @GetMapping
    public Page<Employee> getEmployees(
            @RequestParam(name ="page" ,defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "10")int size) {
        return empRepository.findAll(PageRequest.of(page, size, Sort.by("id").ascending()));
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

    @Transactional
    @DeleteMapping("{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        congeRepository.deleteByEmployeeId(id);
        notificationRepository.deleteByEmployeeId(id);
        absRepository.deleteByEmployeeId(id);
        empRepository.deleteById(id);

    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Employee> updateAll(@PathVariable Long id,
//                                             @RequestBody Employee employee){
//
//        Optional<Employee> emp=empRepository.findById(id);
//        if(emp.isPresent()){
//            return ResponseEntity.status(HttpStatus.ACCEPTED)
//                    .body(empRepository.save(employee));
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateAll(@PathVariable Long id,
                                              @RequestBody Employee employee) {

        return empRepository.findById(id)
                .map(emp -> {
                    emp.setFirstName(employee.getFirstName());
                    emp.setLastName(employee.getLastName());
                    emp.setEmail(employee.getEmail());
                    emp.setPhone(employee.getPhone());
                    emp.setDepartment(employee.getDepartment());
                    emp.setPosition(employee.getPosition());
                    emp.setSalary(employee.getSalary());
                    emp.setHireDate(employee.getHireDate());
                    emp.setCin(employee.getCin());
                    emp.setAdresse(employee.getAdresse());
                    emp.setAge(employee.getAge());
                    emp.setDiplome(employee.getDiplome());
                    emp.setGender(employee.getGender());
                    emp.setSituationfamiliar(employee.getSituationfamiliar());
                    emp.setExperience(employee.getExperience());

                    Employee updated = empRepository.save(emp);

                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
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
            if(employee.getCin()!=null){
                emp.setCin(employee.getCin());
            }
            if(employee.getAdresse()!=null){
                emp.setAdresse(employee.getAdresse());
            }
            if(employee.getAge()!=0){
                emp.setAge(employee.getAge());
            }
            if(employee.getDiplome()!=null){
                emp.setDiplome(employee.getDiplome());
            }
            if(employee.getGender()!=null){
                emp.setGender(employee.getGender());
            }
            if(employee.getSituationfamiliar()!=null){
                emp.setSituationfamiliar(employee.getSituationfamiliar());
            }
            if(employee.getExperience()!=0){
                emp.setExperience(employee.getExperience());
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

//  --------------------liste des employes sans paggination


    @GetMapping("/all")
    public List<Employee> getAllEmp(){
        return empRepository.findAll();
    }







}
