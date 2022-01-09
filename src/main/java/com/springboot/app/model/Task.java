package com.springboot.app.model;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.ToString;


@Entity
@Table(name = "tbl_task")
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String title;

    private String accountable;

    private LocalDate deadline;

    private String description;

    private boolean completed;

    // CONSTRUCTOR
    // Empty constructor might get removed.
    public Task() {
    }

    // GETTER and SETTER
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccountable() {
        return accountable;
    }

    public void setAccountable(String accountable) {
        this.accountable = accountable;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
