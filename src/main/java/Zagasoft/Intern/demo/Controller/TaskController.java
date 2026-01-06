package Zagasoft.Intern.demo.Controller;

import Zagasoft.Intern.demo.Entity.Status;
import Zagasoft.Intern.demo.Entity.Task;
import Zagasoft.Intern.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestBody Task task,
            @RequestHeader("x-user-role") String role) {
        return new ResponseEntity<>(taskService.createTask(task, role), HttpStatus.CREATED);
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusUpdate) {
        Status newStatus = Status.valueOf(statusUpdate.get("status").toLowerCase());
        return ResponseEntity.ok(taskService.updateStatus(id, newStatus));
    }


    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Long assignedTo,
            @RequestParam(required = false) String dueBefore) {

        LocalDate date = (dueBefore != null) ? LocalDate.parse(dueBefore) : null;
        return ResponseEntity.ok(taskService.getAllTasks(status, assignedTo, date));

    }
}
