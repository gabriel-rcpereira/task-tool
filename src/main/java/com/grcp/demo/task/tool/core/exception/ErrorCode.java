package com.grcp.demo.task.tool.core.exception;

public enum ErrorCode {
    TASK_NOT_FOUND("001", "Task not found");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
