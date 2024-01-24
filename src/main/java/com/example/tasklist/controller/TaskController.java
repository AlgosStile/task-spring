package com.example.tasklist.controller;

import com.example.tasklist.repositories.TaskRepository;
import com.example.tasklist.service.Task;
import com.example.tasklist.status.TaskStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Контроллер для управления задачами.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    /**
     * Конструктор для TaskController.
     *
     * @param taskRepository Репозиторий Task
     */
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Добавляет новую задачу.
     *
     * @param task Новая задача
     * @return Добавленная задача
     */
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        task.setCreationDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    /**
     * Получает все задачи.
     *
     * @return Список всех задач
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Получает задачи по статусу.
     *
     * @param status Статус задачи
     * @return Список задач с указанным статусом
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    /**
     * Обновляет статус задачи.
     *
     * @param id   Идентификатор задачи для обновления
     * @param task Обновленная задача
     * @return Обновленная задача
     */
    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setStatus(task.getStatus());
            return taskRepository.save(existingTask);
        } else {
            return null;
        }
    }

    /**
     * Удаляет задачу по идентификатору.
     *
     * @param id Идентификатор задачи для удаления
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
