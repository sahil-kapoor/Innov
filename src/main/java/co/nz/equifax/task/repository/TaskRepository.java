package co.nz.equifax.task.repository;

import co.nz.equifax.task.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository  extends JpaRepository<Task, Long> {
}
