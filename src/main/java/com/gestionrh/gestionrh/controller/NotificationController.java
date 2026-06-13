package com.gestionrh.gestionrh.controller;

import com.gestionrh.gestionrh.entities.Notification;
import com.gestionrh.gestionrh.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {
    private NotificationRepository notificationRepository;


    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    public List<Notification> getByEmployee(@PathVariable Long id) {
        return notificationRepository.findByEmployeeId(id);
    }





}
