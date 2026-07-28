package com.grcp.demo.task.tool.entrypoint.rest.controller;

import com.grcp.demo.task.tool.core.model.Task;
import com.grcp.demo.task.tool.core.model.TaskPage;
import com.grcp.demo.task.tool.core.model.TaskStatus;
import com.grcp.demo.task.tool.core.service.TaskService;
import com.grcp.demo.task.tool.entrypoint.rest.model.TaskPageResponse;
import com.grcp.demo.task.tool.entrypoint.rest.model.TaskRequest;
import com.grcp.demo.task.tool.entrypoint.rest.model.TaskResponse;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/api/v1/tasks")
    public ResponseEntity<TaskResponse> postTask(@RequestBody TaskRequest taskRequest) {
        Task newTask = taskService.createTask(taskRequest.description(), taskRequest.dueDateAt());
        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(toResponse(newTask));
    }

    @GetMapping("/api/v1/tasks")
    public ResponseEntity<TaskPageResponse> getTasks(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) int size,
            @RequestParam(required = false) TaskStatus status) {
        TaskPage taskPage = taskService.findTasks(page, size, status);
        TaskPageResponse response = new TaskPageResponse(
                taskPage.items().stream().map(TaskController::toResponse).toList(),
                taskPage.page(),
                taskPage.size(),
                taskPage.totalItems(),
                taskPage.totalPages());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/v1/tasks/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") Long id) {
        Task task = taskService.findTaskById(id);
        TaskResponse taskResponse = toResponse(task);
        return ResponseEntity.ok().body(taskResponse);
    }

    @PatchMapping("/api/v1/tasks/{id}/status/{status}")
    public ResponseEntity<Void> patchStatus(@PathVariable("id") Long id, @PathVariable("status") TaskStatus status) {
        taskService.updateTaskStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/v1/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    private static TaskResponse toResponse(Task task) {
        return new TaskResponse(task.id(), task.description(), task.dueDateAt(), task.status());
    }
}
