package com.grcp.demo.task.tool.core.model;

import java.util.List;

public record TaskPage(
        List<Task> items,
        int page,
        int size,
        long totalItems,
        int totalPages) {
}
