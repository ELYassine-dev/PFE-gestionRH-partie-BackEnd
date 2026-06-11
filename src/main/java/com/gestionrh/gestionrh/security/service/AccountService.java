package com.gestionrh.gestionrh.security.service;

import com.gestionrh.gestionrh.security.entities.Role;
import com.gestionrh.gestionrh.security.entities.Users;


public interface AccountService {

    Users addNewUser(String name,String password,String email,boolean enabled,String confirmePassword);
    Role addNewRole(String name);
    void addRoleToUsers(Role role,Users users);
    void removeRoleFromUsers(Role role,Users users);
    void deleteUsers(Users users);

}
