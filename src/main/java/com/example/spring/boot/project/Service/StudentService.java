package com.example.spring.boot.project.Service;

import com.example.spring.boot.project.Model.Student;
import com.example.spring.boot.project.Model.User;
import com.example.spring.boot.project.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo ;
    public Optional<Student> getStudentByUsername(String username)
    {
        return studentRepo.findByUserName(username);
    }
    public Student SaveStudent(Student student) {
        User savedUser = new User();
        student.setUser(savedUser);
        Student savedStudent = studentRepo.save(student);
        System.out.println("Saved student with ID: " + savedStudent.getId());
        return savedStudent;
    }


}
