package com.gestionrh.gestionrh.security.service;

import com.gestionrh.gestionrh.security.entities.Role;
import com.gestionrh.gestionrh.security.entities.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Override
    public Users addNewUser(String name, String password, String email, boolean enabled, String confirmePassword) {
        return null;
    }

    @Override
    public Role addNewRole(String name) {
        return null;
    }

    @Override
    public void addRoleToUsers(Role role, Users users) {

    }

    @Override
    public void removeRoleFromUsers(Role role, Users users) {

    }

    @Override
    public void deleteUsers(Users users) {

    }
}
