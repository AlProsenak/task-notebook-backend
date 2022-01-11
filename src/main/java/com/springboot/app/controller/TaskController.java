package com.springboot.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.springboot.app.model.Task;
import com.springboot.app.service.TaskService;


@CrossOrigin(origins="*")
//@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskService taskService;

    @Autowired
    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") long id) {
        return taskService.getTask(id);
    }

    @GetMapping("/tasks/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() {
        return taskService.getAllCompletedTasks();
    }

    // POST
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task taskBody) {
        return taskService.createTask(taskBody);
    }

    // PUT
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable("id") long id,
            @RequestBody Task taskBody
    ) {
        return taskService.updateTask(id, taskBody);
    }

    // DELETE
    @DeleteMapping("/tasks")
    public ResponseEntity<HttpStatus> deleteAllTasks() {
        return taskService.deleteAllTasks();
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
        return taskService.deleteTask(id);
    }

    @DeleteMapping("/tasks/completed")
    public ResponseEntity<HttpStatus> deleteAllCompletedTasks() {
        return taskService.deleteAllCompletedTasks();
    }
}
