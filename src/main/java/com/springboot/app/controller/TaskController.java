package com.springboot.app.controller;

import java.util.List;

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

    @GetMapping("/tasks/completed-{completed}")
    public ResponseEntity<List<Task>> getAllCompletedTasks(
            @PathVariable("completed") boolean completed
    ) {
        return taskService.getAllCompletedTasks(completed);
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

    @DeleteMapping("/tasks/completed-{completed}")
    public ResponseEntity<HttpStatus> deleteAllCompletedTasks(
            @PathVariable("completed")boolean completed
    ) {
        return taskService.deleteAllCompletedTasks(completed);
    }
}
