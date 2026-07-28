package com.grcp.demo.task.tool.entrypoint.rest.model;

import com.grcp.demo.task.tool.core.model.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "TaskResponse", description = "Representation of a task returned by the API.")
public record TaskResponse(
        @Schema(description = "Unique task identifier.", example = "42")
        Long id,
        @Schema(description = "Human-readable task description.", example = "Prepare quarterly report")
        String description,
        @Schema(description = "Task due date and time in ISO-8601 format.", example = "2026-08-01T09:30:00")
        LocalDateTime dueDateAt,
        @Schema(
                description = "Current lifecycle status for the task.",
                allowableValues = {"TODO", "IN_PROGRESS", "DONE", "CANCELLED"},
                example = "TODO")
        TaskStatus status) {
}
