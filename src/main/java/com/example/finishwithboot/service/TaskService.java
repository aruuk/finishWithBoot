package com.example.finishwithboot.service;

import com.example.finishwithboot.model.Task;

import java.util.List;

public interface TaskService {

    void saveTask(Task task, Long id);

    void updateTask(Long id, Task task) ;

    void deleteTask(Long id);

    List<Task> getTasks(Long id);

    Task getTaskById(Long id);
}
