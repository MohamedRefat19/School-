package com.example.spring.boot.project.Repository;

import com.example.spring.boot.project.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepo extends JpaRepository <Teacher , Long> {
    Optional<Teacher> findByUserName(String username);
    List<Teacher> findAll();
}
