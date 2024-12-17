package com.example.spring.boot.project.Controller;

import com.example.spring.boot.project.Model.Task;
import com.example.spring.boot.project.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService ;
    @GetMapping("/{id}")
    @PreAuthorize("Teacher")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
        Task task = taskService.getTaskBYID(id);
        return ResponseEntity.ok(task);
    }

    // Create a new task
    @PostMapping("/create")
    @PreAuthorize("Teacher")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createtask(task);
        return ResponseEntity.ok(createdTask);
    }

    // Update an existing task
    @PutMapping("/update/{id}")
    @PreAuthorize("Teacher")
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updatatask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    // Delete a task by ID
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("Teacher")
    public ResponseEntity<String> deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task with ID " + id + " has been deleted");
    }

    // Assign a task to a student
    @PostMapping("/{taskId}/assign")
    @PreAuthorize("Teacher")
    public ResponseEntity<String> assignTaskToStudent(@PathVariable Long taskId, @RequestParam String username) {
        String result = taskService.assignTaskToStudent(taskId, username);
        return ResponseEntity.ok(result);
    }

}
