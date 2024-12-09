package com.example.spring.boot.project.Service;

import com.example.spring.boot.project.Model.Teacher;
import com.example.spring.boot.project.Model.User;
import com.example.spring.boot.project.Repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo ;
    public Teacher SaveTeacher(Teacher teacher) {
        User savedUser = new User();
        teacher.setUser(savedUser);
        Teacher savedteacher = teacherRepo.save(teacher);
        System.out.println("Saved teacher with ID: " + savedteacher.getId());
        return savedteacher;
    }
    public Teacher findrandomteacher(){
        List<Teacher> teachers = teacherRepo.findAll();
        if (teachers.isEmpty())
        {
            throw new RuntimeException("No teachers available to assign points.");
        }
        Random random = new Random();
        Teacher assignedTeacher = teachers.get(random.nextInt(teachers.size()));
        return teacherRepo.save(assignedTeacher);
    }

}
