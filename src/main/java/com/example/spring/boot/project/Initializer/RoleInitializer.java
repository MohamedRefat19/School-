package com.example.spring.boot.project.Initializer;

import com.example.spring.boot.project.Model.Role;
import com.example.spring.boot.project.Repository.RoleRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer {
    @Autowired
    private RoleRepo roleRepo;

    @PostConstruct
    public void initRoles() {
        if (roleRepo.findByName(Role.ROLE_STUDENT).isEmpty()) {
            roleRepo.save(new Role(Role.ROLE_STUDENT));
        }
        if (roleRepo.findByName(Role.ROLE_TEACHER).isEmpty()) {
            roleRepo.save(new Role(Role.ROLE_TEACHER));
        }

    }
}