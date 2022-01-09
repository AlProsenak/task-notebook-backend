package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Task;


@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

}
