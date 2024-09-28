package com.example.restapi.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;

// The model, it represents the to do items(task object)
// This model is used in the Repository. It's the object that is handled there.
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String tittle;
    private String description;
    @NonNull
    private Boolean completed;

    public Task(long id, @NonNull String tittle, String description, @NonNull Boolean completed) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean isCompleted() {

        return completed = true;
    }
}
