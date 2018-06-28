package co.nz.equifax.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.nz.equifax.task.entities.Task;

public interface TaskRepository  extends JpaRepository<Task, Long> {
}
