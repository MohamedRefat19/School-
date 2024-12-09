package com.example.spring.boot.project.Repository;

import com.example.spring.boot.project.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository <Student , Long> {
    Optional<Student> findByUserName(String username);
    @Override
    Optional<Student> findById(Long id);

}
