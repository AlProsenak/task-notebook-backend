package com.springboot.app.model;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "tbl_task")
@NoArgsConstructor
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private String title;

    private String accountable;

    @Column(nullable=false)
    private String description;

    private LocalDate deadline;

    private LocalDate completionDate;

    private boolean completed;

    // CONSTRUCTOR
    public Task(
            String title,
            String accountable,
            String description,
            LocalDate deadline,
            LocalDate completionDate,
            boolean completed
    ) {
        this.title = title;
        this.accountable = accountable;
        this.description = description;
        this.deadline = deadline;
        this.completionDate = completionDate;
        this.completed = completed;
    }

    // GETTER and SETTER

    public long getId() {
        return id;
    }

    // Very likely not needed.
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
