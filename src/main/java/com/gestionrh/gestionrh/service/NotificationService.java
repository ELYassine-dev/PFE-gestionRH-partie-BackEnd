package com.gestionrh.gestionrh.service;

import com.gestionrh.gestionrh.entities.Employee;
import com.gestionrh.gestionrh.entities.Notification;
import com.gestionrh.gestionrh.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
public class NotificationService {

    private NotificationRepository notificationRepository;

    public void sendNotification(Employee employee, String message) {

        Notification n = new Notification();
        n.setEmployee(employee);
        n.setMessage(message);
        n.setRead(false);
        n.setCreateAt(LocalDateTime.now());

//        Notification n1 = Notification.builder()
//        .setEmployee(employee)
//        .setMessage(message)
//        .setRead(false)
//        .setCreateAt(LocalDateTime.now())
//                .build();
        notificationRepository.save(n);
    }
}
