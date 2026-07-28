package com.grcp.demo.task.tool.entrypoint.rest.model;

import java.time.LocalDateTime;

public record TaskRequest(String description, LocalDateTime dueDateAt) {
}
