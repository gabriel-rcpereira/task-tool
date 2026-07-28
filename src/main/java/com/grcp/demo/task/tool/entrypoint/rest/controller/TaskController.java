package com.grcp.demo.task.tool.entrypoint.rest.controller;

import com.grcp.demo.task.tool.core.model.Task;
import com.grcp.demo.task.tool.core.model.TaskPage;
import com.grcp.demo.task.tool.core.model.TaskStatus;
import com.grcp.demo.task.tool.core.service.TaskService;
import com.grcp.demo.task.tool.entrypoint.rest.model.TaskPageResponse;
import com.grcp.demo.task.tool.entrypoint.rest.model.TaskRequest;
import com.grcp.demo.task.tool.entrypoint.rest.model.TaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Tasks", description = "Operations for managing tasks.")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/api/v1/tasks")
    @Operation(summary = "Create task", description = "Creates a new task with an initial TODO status.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Task created successfully",
                    content = @Content(schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(responseCode = "400", description = "Request body is invalid")
    })
    public ResponseEntity<TaskResponse> postTask(@RequestBody TaskRequest taskRequest) {
        Task newTask = taskService.createTask(taskRequest.description(), taskRequest.dueDateAt());
        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(toResponse(newTask));
    }

    @GetMapping("/api/v1/tasks")
    @Operation(summary = "List tasks", description = "Returns paginated tasks, optionally filtered by status.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task page returned successfully",
                    content = @Content(schema = @Schema(implementation = TaskPageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Pagination or filter input is invalid")
    })
    public ResponseEntity<TaskPageResponse> getTasks(
            @Parameter(in = ParameterIn.QUERY, description = "Zero-based page index.", example = "0")
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @Parameter(in = ParameterIn.QUERY, description = "Maximum number of items per page.", example = "20")
            @RequestParam(defaultValue = "20") @Min(1) int size,
            @Parameter(
                    in = ParameterIn.QUERY,
                    description = "Optional status filter.",
                    schema = @Schema(
                            description = "Lifecycle status for a task.",
                            allowableValues = {"TODO", "IN_PROGRESS", "DONE", "CANCELLED"},
                            example = "TODO"))
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
    @Operation(summary = "Get task by id", description = "Returns a single task by its identifier.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task returned successfully",
                    content = @Content(schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResponse> getTaskById(
            @Parameter(description = "Task identifier.", example = "42") @PathVariable("id") Long id) {
        Task task = taskService.findTaskById(id);
        TaskResponse taskResponse = toResponse(task);
        return ResponseEntity.ok().body(taskResponse);
    }

    @PatchMapping("/api/v1/tasks/{id}/status/{status}")
    @Operation(summary = "Update task status", description = "Updates lifecycle status for an existing task.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<Void> patchStatus(
            @Parameter(description = "Task identifier.", example = "42") @PathVariable("id") Long id,
            @Parameter(
                    description = "New task status.",
                    schema = @Schema(
                            description = "Lifecycle status for a task.",
                            allowableValues = {"TODO", "IN_PROGRESS", "DONE", "CANCELLED"},
                            example = "IN_PROGRESS"))
            @PathVariable("status") TaskStatus status) {
        taskService.updateTaskStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/v1/tasks/{id}")
    @Operation(summary = "Delete task", description = "Deletes an existing task by its identifier.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<Void> deleteTask(
            @Parameter(description = "Task identifier.", example = "42") @PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    private static TaskResponse toResponse(Task task) {
        return new TaskResponse(task.id(), task.description(), task.dueDateAt(), task.status());
    }
}
