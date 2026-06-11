package com.gestionrh.gestionrh.repository;

import com.gestionrh.gestionrh.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
