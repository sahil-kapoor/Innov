package co.nz.equifax.task.controllers;

import co.nz.equifax.task.entities.Task;
import co.nz.equifax.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskRepository taskRepository;

  public TaskController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @PostMapping
  public void addTask(@RequestBody Task task) {
    taskRepository.save(task);
  }

  @GetMapping
  public List<Task> getTasks() {
    return taskRepository.findAll();
  }

  @PutMapping("/{id}")
  public void editTask(@PathVariable long id, @RequestBody Task task) {
    Task existingTask = taskRepository.findById(id).get();
    Assert.notNull(existingTask, "Task not found");
    existingTask.setDescription(task.getDescription());
    taskRepository.save(existingTask);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable long id) {
    taskRepository.deleteById(id);
  }
}
