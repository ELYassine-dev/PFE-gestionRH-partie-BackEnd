package com.gestionrh.gestionrh.repository;

import com.gestionrh.gestionrh.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employee, Long> {


        @Query("""
       SELECT e FROM Employee e
       WHERE LOWER(CONCAT(e.firstName, ' ', e.lastName))
             LIKE LOWER(CONCAT('%', :keyword, '%'))
       """)
        List<Employee> searchByFullName(@Param("keyword") String keyword);

//        List<Employee> findByFirstNameContainingIgnoreCase(String keyword);

//        List<Employee> findAllByOrderByIdAsc();
}
