package com.grcp.demo.task.tool.entrypoint.rest.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "TaskPageResponse", description = "Paginated response for task listings.")
public record TaskPageResponse(
        @ArraySchema(schema = @Schema(implementation = TaskResponse.class), arraySchema = @Schema(description = "Tasks returned for current page."))
        List<TaskResponse> items,
        @Schema(description = "Zero-based page index returned by the API.", example = "0")
        int page,
        @Schema(description = "Maximum number of items requested for the page.", example = "20")
        int size,
        @Schema(description = "Total number of matching tasks across all pages.", example = "42")
        long totalItems,
        @Schema(description = "Total number of pages available for current query.", example = "3")
        int totalPages) {
}
