package Zagasoft.Intern.demo.Service;

import Zagasoft.Intern.demo.Entity.Status;
import Zagasoft.Intern.demo.Entity.Task;
import Zagasoft.Intern.demo.Entity.User;

import java.time.LocalDate;
import java.util.List;

public interface TaskService
{
    User createUser(User user);

    Task createTask(Task task, String userRole);

    Task updateStatus(Long taskId, Status newStatus);

    List<Task> getAllTasks(Status status, Long assignedTo, LocalDate dueBefore);
}
