package com.example.spring.boot.project.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double grade;

    @ManyToMany(mappedBy = "students")
    private Set<Task> tasks;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
