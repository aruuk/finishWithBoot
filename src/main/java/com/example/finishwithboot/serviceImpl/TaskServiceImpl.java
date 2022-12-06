package com.example.finishwithboot.serviceImpl;

import com.example.finishwithboot.model.Lesson;
import com.example.finishwithboot.model.Task;
import com.example.finishwithboot.repository.LessonRepository;
import com.example.finishwithboot.repository.TaskRepository;
import com.example.finishwithboot.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Override
    public void saveTask(Task task, Long id) {
        Lesson lesson = lessonRepository.findById(id).get();
        lesson.addTask(task);
        task.setLesson(lesson);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, Task task) {
            Task task1 = taskRepository.findById(id).get();
            task1.setTaskName(task.getTaskName());
            task1.setTaskText(task.getTaskText());
            task1.setDeadline(task.getDeadline());
            taskRepository.save(task1);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).get();
        taskRepository.delete(task);
    }

    @Override
    public List<Task> getTasks(Long id) {
        return taskRepository.getTaskByLessonId(id);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }
}
