package com.gestionrh.gestionrh.security.service;

import com.gestionrh.gestionrh.security.entities.Role;
import com.gestionrh.gestionrh.security.entities.Users;
import com.gestionrh.gestionrh.security.repository.RoleRepository;
import com.gestionrh.gestionrh.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public Users addNewUser(String username, String password, String email, boolean enabled, String confirmePassword) {
       Users user = userRepository.findByUsername(username);
        if(user!=null) throw new RuntimeException("Username already exists");
        if(!password.equals(confirmePassword))throw new IllegalArgumentException("Passwords do not match");
       user= Users.builder()
                   .username(username)
                   .password(password)
                   .email(email)
                   .enabled(enabled)
                   .build();
           return userRepository.save(user);

    }


    @Override
    public Role addNewRole(String role) {
        Role roles=roleRepository.findByRole(role);
        if(roles!=null) throw new RuntimeException("Role already exists");
        roles = Role.builder()
                .role(role)
                .build();
        return roleRepository.save(roles);
    }


    @Override
    public void addRoleToUsers(String role, String username) {
        Users user=userRepository.findByUsername(username);
        if(user==null) throw new RuntimeException("Username do not exists");
        Role roles=roleRepository.findByRole(role);
        if(roles==null) throw new RuntimeException("Role already exists");
        user.getRoles().add(roles);
        //optional
        userRepository.save(user);


    }

    @Override
    public void removeRoleFromUsers(String role, String username) {
        Users user=userRepository.findByUsername(username);
        if(user==null) throw new RuntimeException("Username do not exists");
        Role roles=roleRepository.findByRole(role);
        if(roles==null) throw new RuntimeException("Role already exists");
        user.getRoles().remove(roles);
        //optional
        userRepository.save(user);

    }

    @Override
    public Users loadUsersByUsername(String username) {
        return userRepository.findByUsername(username);

    }

}
