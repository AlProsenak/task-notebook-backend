package com.springboot.app;

import java.time.LocalDate;
import java.util.List;

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
        List<Task> taskList = taskRepository.findAll();

        if (taskList.isEmpty() || taskList.size() == 0) {
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
                            "Playback speed 1.5 is your friend ... ",
                            LocalDate.of(2022, 1, 9),
                            currentDate,
                            true
                    )
            );

            System.out.println("\nDatabase empty. Added mock data from DataLoader ... ");
        } else {
            System.out.println("\nDatabase populated. Skip adding mock data from DataLoader ... ");
        }
    }
}
