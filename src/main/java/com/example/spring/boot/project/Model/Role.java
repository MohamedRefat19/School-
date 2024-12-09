package com.example.spring.boot.project.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public static final String ROLE_STUDENT = "Student";
    public static final String ROLE_TEACHER = "Teacher";

    public Role() {}
    public Role(String name) {
        this.name = name;
    }


}
