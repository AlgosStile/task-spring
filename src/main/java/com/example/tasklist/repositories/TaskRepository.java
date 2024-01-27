package com.example.tasklist.repositories;

import com.example.tasklist.domain.Task;
import com.example.tasklist.status.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для сущностей Task.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Получает задачи по статусу.
     *
     * @param status Статус задачи
     * @return Список задач с указанным статусом
     */
    List<Task> findByStatus(TaskStatus status);
}
