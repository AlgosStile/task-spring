# Spring Data работа с базами данных

Условие:
Приложение для управления списком задач с использованием Spring Boot и Spring Data JPA. 
Требуется реализовать следующие функции:

Добавление задачи. 
Подсказка метод в контроллере: @PostMapping public Task addTask(@RequestBody Task task)

Просмотр всех задач. 
Метод в контроллере: @GetMapping public List<Task> getAllTasks()

Просмотр задач по статусу (например, "завершена", "в процессе", "не начата").
Метод в контроллере: @GetMapping("/status/{status}") public List<Task> getTasksByStatus(@PathVariable TaskStatus status)

Изменение статуса задачи.
Метод в контроллере: @PutMapping("/{id}") public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task)

Удаление задачи.
Метод в контроллере:
@DeleteMapping("/{id}")
public void deleteTask(@PathVariable Long id)

Репозитроий.
public interface TaskRepository extends JpaRepository<Task, Long>

Структура задачи(класс Task):
- ID (автоинкрементное)(тип Long)
- Описание (не может быть пустым)(тип String)
- Статус (одно из значений: "не начата", "в процессе", "завершена")(Тип TaskStatus )
- Дата создания (автоматически устанавливается при создании задачи)(Тип LocalDateTime)
