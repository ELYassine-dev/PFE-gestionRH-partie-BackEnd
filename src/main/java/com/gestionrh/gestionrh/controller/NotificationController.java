package com.gestionrh.gestionrh.controller;

import com.gestionrh.gestionrh.entities.Notification;
import com.gestionrh.gestionrh.repository.EmployeRepository;
import com.gestionrh.gestionrh.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class NotificationController {
    private NotificationRepository notificationRepository;
    private EmployeRepository emprepository;


    @GetMapping
    public Page<Notification> getAllNotifications( @RequestParam(name="page",defaultValue = "0") int page,
                                                   @RequestParam(name="size",defaultValue = "10") int size
    ) {
        return notificationRepository.findAll(PageRequest.of(page,size));
    }

    @GetMapping("/employee/{id}")
    public List<Notification> getByEmployee(@PathVariable Long id
                                             ) {
        return notificationRepository.findByEmployeeId(id);
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(@PathVariable Long id)
    {
        return notificationRepository.findById(id)
                .map(n -> {
                    n.setRead(true);
                    return ResponseEntity.ok(notificationRepository.save(n));
                })
                .orElse(ResponseEntity.notFound().build());
    }



    @GetMapping("/search")
    public List<Notification> searchByEmployeeName(@RequestParam String name) {

        return notificationRepository.searchByEmployeeName(name);
    }


}
