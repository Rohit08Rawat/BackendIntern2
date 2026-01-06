package Zagasoft.Intern.demo.Service;

import Zagasoft.Intern.demo.Entity.Status;
import Zagasoft.Intern.demo.Entity.Task;
import Zagasoft.Intern.demo.Entity.User;
import Zagasoft.Intern.demo.Repo.TaskRepository;
import Zagasoft.Intern.demo.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService
{
       @Autowired
        private TaskRepository taskRepository;

        @Autowired
        private UserRepository userRepository;

        @Override
        public User createUser(User user) {
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new RuntimeException("Duplicate email: " + user.getEmail());
            }
            return userRepository.save(user);
        }

        @Override
        public Task createTask(Task task, String userRole) {
            if (!"admin".equalsIgnoreCase(userRole)) {
                throw new RuntimeException("Unauthorized: Only admin can create tasks");
            }

            if (!userRepository.existsById(task.getAssignedTo().getId())) {
                throw new RuntimeException("User not found");
            }

            if (task.getDueDate().isBefore(LocalDate.now())) {
                throw new RuntimeException("DueDate must be a future date");
            }

            long pendingCount = taskRepository.countByAssignedToIdAndStatus(task.getAssignedTo().getId(), Status.pending);
            if (pendingCount >= 5) {
                throw new RuntimeException("User already has 5 pending tasks");
            }

            return taskRepository.save(task);
        }

        @Override
        public Task updateStatus(Long taskId, Status newStatus) {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new RuntimeException("Task not found"));

            Status currentStatus = task.getStatus();
            boolean isValid = false;

            if (currentStatus == Status.pending && newStatus == Status.in_progress) {
                isValid = true;
            } else if (currentStatus == Status.in_progress && newStatus == Status.completed) {
                isValid = true;
            }

            if (!isValid) {
                throw new IllegalStateException("Invalid status transition from " + currentStatus + " to " + newStatus);
            }

            task.setStatus(newStatus);
            return taskRepository.save(task);
        }

        @Override
        public List<Task> getAllTasks(Status status, Long assignedTo, LocalDate dueBefore) {
            return taskRepository.findAll();
        }
}
