package com.grcp.demo.task.tool.entrypoint.rest.model;

import java.util.List;

public record TaskPageResponse(
        List<TaskResponse> items,
        int page,
        int size,
        long totalItems,
        int totalPages) {
}
