package com.gestionrh.gestionrh.repository;

import com.gestionrh.gestionrh.entities.Employee;
import com.gestionrh.gestionrh.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findByEmployeeId(Long employeeId);
//  Page<Notification> findAll(eable);


    @Query("""
SELECT n FROM Notification n
WHERE LOWER(n.employee.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(n.employee.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Notification> searchByEmployeeName(@Param("keyword") String keyword);

    void deleteByEmployeeId(Long id);


}
