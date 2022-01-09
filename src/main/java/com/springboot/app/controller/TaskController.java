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

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") long id) {
        try {
            Optional<Task> task = taskRepository.findById(id);

            if (task.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tasks/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() {
        try {
            List<Task> taskList = taskRepository.findByCompleted(true);

            if (taskList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(taskList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task taskBody) {
        try {
            Task _task = taskRepository.save(
                    new Task(
                        taskBody.getTitle(),
                        taskBody.getAccountable(),
                        taskBody.getDeadline(),
                        taskBody.getDescription(),
                        false
                    )
            );

            return new ResponseEntity<>(_task, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable("id") long id,
            @RequestBody Task taskBody
    ) {
        try {
            Optional<Task> task = taskRepository.findById(id);

            if (task.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Task _task = task.get();

            _task.setTitle(taskBody.getTitle());
            _task.setAccountable(taskBody.getAccountable());
            _task.setDeadline(taskBody.getDeadline());
            _task.setDescription(taskBody.getDescription());
            _task.setCompleted(taskBody.isCompleted());

            return new ResponseEntity<>(
                    taskRepository.save(_task),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    @DeleteMapping("/tasks")
    public ResponseEntity<HttpStatus> deleteAllTasks() {
        try {
            taskRepository.deleteAll();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
        try {
            Optional<Task> task = taskRepository.findById(id);

            if (task.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            taskRepository.delete(task.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tasks/completed")
    public ResponseEntity<HttpStatus> deleteAllCompletedTasks() {
        try {
            List<Task> taskList = taskRepository.findByCompleted(true);

            taskRepository.deleteAll(taskList);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
