package com.example.tasklist.controller;

import com.example.tasklist.repositories.TaskRepository;
import com.example.tasklist.domain.Task;
import com.example.tasklist.status.TaskStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Контроллер для управления задачами.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    /**
     * Добавляет новую задачу.
     *
     * @param task Новая задача
     * @return Добавленная задача
     */
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        task.setCreationDate(LocalDateTime.now());
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(savedTask);
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
    public List<Task> getTasksByStatus(@PathVariable String status) {
        return taskRepository.findByStatus(TaskStatus.valueOf(status));
    }

    /**
     * Обновляет статус задачи.
     *
     * @param id   Идентификатор задачи для обновления
     * @param task Обновленная задача
     * @return Обновленная задача
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setStatus(task.getStatus());
            Task updatedTask = taskRepository.save(existingTask);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаляет задачу по идентификатору.
     *
     * @param id Идентификатор задачи для удаления
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
