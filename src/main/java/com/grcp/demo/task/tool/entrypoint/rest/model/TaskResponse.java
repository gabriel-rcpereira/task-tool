package com.grcp.demo.task.tool.entrypoint.rest.model;

import com.grcp.demo.task.tool.core.model.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse(Long id, String description, LocalDateTime dueDateAt, TaskStatus status) {
}
