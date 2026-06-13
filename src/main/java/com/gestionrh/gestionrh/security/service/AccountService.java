package com.gestionrh.gestionrh.security.service;

import com.gestionrh.gestionrh.security.entities.Role;
import com.gestionrh.gestionrh.security.entities.Users;


public interface AccountService {

    Users addNewUser(String username,String password,String email,boolean enabled,String confirmePassword);
    Role addNewRole(String role);
    void addRoleToUsers(String role,String username);
    void removeRoleFromUsers(String role,String username);
    Users loadUsersByUsername(String username);
}
