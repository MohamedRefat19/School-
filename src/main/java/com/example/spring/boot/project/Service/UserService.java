package com.example.spring.boot.project.Service;

import com.example.spring.boot.project.DTO.RegisterRequest;
import com.example.spring.boot.project.Model.Role;
import com.example.spring.boot.project.Model.Student;
import com.example.spring.boot.project.Model.Teacher;
import com.example.spring.boot.project.Model.User;
import com.example.spring.boot.project.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
   @Autowired
    private UserRepo userRepo ;
   @Autowired
    private StudentService studentService ;
   @Autowired
    private TeacherService teacherService ;
   @Autowired
    private PasswordEncoder passwordEncoder ;

   public String registerUser(RegisterRequest request){
       if (userRepo.findByUsername(request.getUsername()).isPresent()) {
           throw new RuntimeException("Username is already taken");
       }
       User user = new User() ;
       user.setName(request.getName());
       user.setUsername(request.getUsername());
       user.setPassword(passwordEncoder.encode(request.getPassword()));
       Role role = new Role();
       role.setName(request.getRoleName());
       user.setRole(role);
       User saveduser = userRepo.save(user);
       if ("Student".equalsIgnoreCase(request.getRoleName())) {
           Student student = new Student() ;
           student.setUser(saveduser);
           studentService.SaveStudent(student);
       }
       else if ("Teacher".equalsIgnoreCase(request.getRoleName())){
           Teacher teacher = new Teacher();
           teacher.setUser(saveduser);
           teacherService.SaveTeacher(teacher);
       }
       return "user register successfully" ;
   }

}
