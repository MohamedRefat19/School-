package com.example.spring.boot.project.Repository;

import com.example.spring.boot.project.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository <Task , Long> {
    @Override
    Optional<Task>findById(Long id);
}
