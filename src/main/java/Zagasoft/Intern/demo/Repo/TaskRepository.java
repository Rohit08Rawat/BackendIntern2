package Zagasoft.Intern.demo.Repo;

import Zagasoft.Intern.demo.Entity.Status;
import Zagasoft.Intern.demo.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>
{
    List<Task> findByStatus(Status status);

    List<Task> findByAssignedToId(Long userId);

    List<Task> findByDueDateBefore(LocalDate date);

    List<Task> findByStatusAndAssignedToId(Status status,Long userId);

    public Long countByAssignedToIdAndStatus(Long userId,Status status);

}
