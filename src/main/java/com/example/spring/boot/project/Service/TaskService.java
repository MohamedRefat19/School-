package com.example.spring.boot.project.Service;

import com.example.spring.boot.project.Model.Student;
import com.example.spring.boot.project.Model.Task;
import com.example.spring.boot.project.Model.Teacher;
import com.example.spring.boot.project.Repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo ;
    @Autowired
    private StudentService studentService ;
    @Autowired
    private TeacherService teacherService ;

    public Task getTaskBYID(long id ){
        Optional<Task> task = taskRepo.findById(id);
        return task.orElseThrow(() -> new RuntimeException("task not found with ID: " + id));
    }

    public Task createtask(Task task){
        if (task.getName() == null || task.getName().trim().isEmpty()) {
            throw new RuntimeException("Task name cannot be empty");
        }
        if (task.getPoint() <= 0) {
            throw new RuntimeException("Task points must be greater than 0");
        }
        Task newtask = new Task();
        newtask.setName(task.getName().trim());
        newtask.setPoint(task.getPoint());
        return taskRepo.save(newtask);
    }
    public Task updatatask(long id , Task taskDetails){
        Task existingTask = getTaskBYID(id);
        if (taskDetails.getName() == null || taskDetails.getName().trim().isEmpty()) {
            throw new RuntimeException("Task name cannot be empty");
        }
        if (taskDetails.getPoint() <= 0) {
            throw new RuntimeException("Task points must be greater than 0");
        }
        existingTask.setName(taskDetails.getName().trim());
        existingTask.setPoint(taskDetails.getPoint());
        return taskRepo.save(existingTask);
    }
    public void deleteTask(long id){
        getTaskBYID(id);
        taskRepo.deleteById(id);
    }
    public String assignTaskToStudent(Long taskId, String username) {
        Teacher teacher = teacherService.findrandomteacher();

        Optional<Task> taskOptional = taskRepo.findById(taskId);
        if (taskOptional.isEmpty()) {
            throw new RuntimeException("Task with ID " + taskId + " not found");
        }
        Task task = taskOptional.get();
        Optional<Student> studentOptional = studentService.getStudentByUsername(username);
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student with username " + username + " not found");
        }
        Student student = studentOptional.get();
        task.getStudents().add(student);
        task.setTeacher(teacher);
        taskRepo.save(task);
        return "Task " + task.getName() + " has been assigned to student " + student.getUser().getName();
    }


}
