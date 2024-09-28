package com.example.restapi.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// A service layer.
// This class uses the Repository class(object instance) to make operations in the database.
// And this Service class uses the Task class(model)
// because it is the oject being handled in the operations.
@Service
public class TaskService {
    @Autowired // Spring manages this bean (Dependency Injection).
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
        }

        public Task getTaskById(Long id) {
            return taskRepository.findById(id).orElse(null);
        }

        public Task createTask(Task task) {
            return taskRepository.save(task);
        }

        public Task updateTask(Long id, Task taskDetails) {
            Task task = taskRepository.findById(id).orElse(null);
            if (task != null) {
                task.setTittle(taskDetails.getTittle());
                task.setDescription(taskDetails.getDescription());
                task.setCompleted(taskDetails.isCompleted()); // finish the task
                return taskRepository.save(task);
            }
            return null;
        }

        public void deleteTask(Long id) {
            taskRepository.deleteById(id);
        }
    }
