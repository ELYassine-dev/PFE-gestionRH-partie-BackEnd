package com.gestionrh.gestionrh.security.repository;

import com.gestionrh.gestionrh.security.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long>
{
    Users findByUsername(String username);



}
