package com.springboot.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.app.model.Task;
import com.springboot.app.repository.ITaskRepository;


@Service
public class TaskService {

    private ITaskRepository taskRepository;

    @Autowired
    TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    static boolean RequiredFields(Task taskBody) {
        if (
                taskBody.getTitle() == null ||
                taskBody.getTitle().trim().length() == 0 ||
                taskBody.getDescription() == null ||
                taskBody.getDescription().trim().length() == 0
        ) {
            return false;
        }
        return true;
    }

    // GET
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

    public ResponseEntity<Task> getTask(long id) {
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

    public ResponseEntity<List<Task>> getAllCompletedTasks(boolean completed) {
        try {
            List<Task> taskList = taskRepository.findByCompleted(completed);

            if (taskList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(taskList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // POST
    public ResponseEntity<Task> createTask(Task taskBody) {
        try {
            boolean requiredFields = RequiredFields(taskBody);

            if (!requiredFields) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Task _task = taskRepository.save(
                    new Task(
                            taskBody.getTitle(),
                            taskBody.getAccountable(),
                            taskBody.getDescription(),
                            taskBody.getDeadline(),
                            null,
                            false
                    )
            );

            return new ResponseEntity<>(_task, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // PUT
    public ResponseEntity<Task> updateTask(long id, Task taskBody) {
        try {
            Optional<Task> task = taskRepository.findById(id);

            if (task.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            boolean requiredFields = RequiredFields(taskBody);

            if (!requiredFields) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Task _task = task.get();

            _task.setTitle(taskBody.getTitle());
            _task.setAccountable(taskBody.getAccountable());
            _task.setDescription(taskBody.getDescription());
            _task.setDeadline(taskBody.getDeadline());
            _task.setCompleted(taskBody.isCompleted());

            if (_task.isCompleted()) {
                LocalDate _currentDate = LocalDate.now();
                _task.setCompletionDate(_currentDate);
            } else {
                _task.setCompletionDate(null);
            }

            return new ResponseEntity<>(
                    taskRepository.save(_task),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // DELETE
    public ResponseEntity<HttpStatus> deleteAllTasks() {
        try {
            taskRepository.deleteAll();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<HttpStatus> deleteTask(long id) {
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

    public ResponseEntity<HttpStatus> deleteAllCompletedTasks(
            boolean completed
    ) {
        try {
            List<Task> taskList = taskRepository.findByCompleted(completed);
            taskRepository.deleteAll(taskList);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
