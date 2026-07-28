package com.grcp.demo.task.tool.entrypoint.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "TaskRequest", description = "Payload used to create a task.")
public record TaskRequest(
        @Schema(description = "Human-readable task description.", example = "Prepare quarterly report")
        String description,
        @Schema(description = "Task due date and time in ISO-8601 format.", example = "2026-08-01T09:30:00")
        LocalDateTime dueDateAt) {
}
