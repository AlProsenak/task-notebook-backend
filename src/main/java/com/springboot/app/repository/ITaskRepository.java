package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Task;

import java.util.List;


@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);
}
