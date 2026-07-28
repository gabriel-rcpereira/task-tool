package com.grcp.demo.task.tool.core.model;

import java.time.LocalDateTime;

public record Task(
        Long id,
        String description,
        LocalDateTime dueDateAt,
        TaskStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static Task aNew(String description, LocalDateTime dueDateAt) {
        return new Task(null, description, dueDateAt, TaskStatus.TODO, LocalDateTime.now(), LocalDateTime.now());
    }

    public Task updateStatus(TaskStatus status) {
        return new Task(id, description, dueDateAt, status, this.createdAt, LocalDateTime.now());
    }
}
