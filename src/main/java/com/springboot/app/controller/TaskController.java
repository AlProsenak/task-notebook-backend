package com.springboot.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.springboot.app.model.Task;
import com.springboot.app.repository.ITaskRepository;


@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    ITaskRepository taskRepository;

    // GET
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            List<Task> taskList = taskRepository.findAll();

            if (taskList.isEmpty() || taskList.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST
    @PostMapping("/tasks")
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        try {
            return new ResponseEntity<>(taskRepository.save(task), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT

    // DELETE

}
