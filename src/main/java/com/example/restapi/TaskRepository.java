package com.example.restapi;

import org.springframework.data.jpa.repository.JpaRepository;

//A repository interface to handle database operations. This interface extends the JpaRepository.
//The repository uses the task class(model) in the database operations.
public interface TaskRepository extends JpaRepository<Task, Long> {
}
