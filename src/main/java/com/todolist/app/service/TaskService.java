package com.todolist.app.service;

import com.todolist.app.model.Task;

import java.util.Optional;

public interface TaskService {

    Optional<Task> findById(Long id);

    Iterable<Task> findAll();

    void remove(Long id);

    void save(Task task);
}
