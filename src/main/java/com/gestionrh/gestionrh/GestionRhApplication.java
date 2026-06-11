package com.gestionrh.gestionrh;

import com.gestionrh.gestionrh.entities.Employee;
import com.gestionrh.gestionrh.repository.EmployeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class GestionRhApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionRhApplication.class, args);
    }

//    @Bean
    CommandLineRunner init(EmployeRepository empRepository) {
        return args->{
for(int i=0;i<5;i++){
    Employee emp = new Employee();
    emp.setFirstName("ahmed");
    emp.setLastName("alaoui");
    emp.setDepartment("it");
    emp.setSalary(25.000);
    emp.setEmail("ahmed@gmail.com");
    emp.setPhone("056483256");
    emp.setPosition("devops");
    emp.setHireDate(LocalDate.now());
    empRepository.save(emp);

}


        };
    }
}
