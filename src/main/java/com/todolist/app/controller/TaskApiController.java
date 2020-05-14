package com.todolist.app.controller;

import com.todolist.app.model.Task;
import com.todolist.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/task")
public class TaskApiController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    private ResponseEntity<List<Task>> findAllTask() {
        List<Task> tasks = (List<Task>) taskService.findAll();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(tasks, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        taskService.save(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task){
        Optional<Task> currentTask = taskService.findById(id);
        if (!currentTask.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentTask.get().setTitle(task.getTitle());
        currentTask.get().setCompleted(task.isCompleted());

        taskService.save(currentTask.get());
        return new ResponseEntity<>(currentTask.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        Optional<Task> task = taskService.findById(id);
        if (task.isPresent()) {
            taskService.remove(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
