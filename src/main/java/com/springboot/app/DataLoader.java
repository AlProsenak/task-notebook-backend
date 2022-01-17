package com.springboot.app;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.springboot.app.model.Task;
import com.springboot.app.repository.ITaskRepository;


// Creates initial data on application start.
@Component
public class DataLoader implements ApplicationRunner {

    private ITaskRepository taskRepository;

    @Autowired
    public DataLoader(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void run(ApplicationArguments args) {
        LocalDate currentDate = LocalDate.now();

        taskRepository.save(
                new Task(
                    "Create TODO application",
                    "Alex",
                    "Create task managing notebook application using Spring Boot framework.",
                    LocalDate.of(2022, 1, 17),
                    null,
                    false
                )
        );

        taskRepository.save(
                new Task(
                    "Brew some tea",
                    "Kettle",
                    "Stay hydrated and make sure it is black. You will need it!",
                    LocalDate.of(2022, 1, 7),
                    currentDate,
                    true
                )
        );

        taskRepository.save(
                new Task(
                        "Watch some tutorials",
                        "YouTube",
                        "Playback speed 1.25 is your friend ... ",
                        LocalDate.of(2022, 1, 9),
                        currentDate,
                        true
                )
        );
    }
}
