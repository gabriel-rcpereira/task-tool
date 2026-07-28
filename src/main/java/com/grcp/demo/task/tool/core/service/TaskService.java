package com.grcp.demo.task.tool.core.service;

import com.grcp.demo.task.tool.core.exception.ErrorCode;
import com.grcp.demo.task.tool.core.exception.NotFoundException;
import com.grcp.demo.task.tool.core.gateway.TaskGateway;
import com.grcp.demo.task.tool.core.model.Task;
import com.grcp.demo.task.tool.core.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskGateway taskGateway;

    public TaskService(TaskGateway taskGateway) {
        this.taskGateway = taskGateway;
    }

    public Task createTask(String description, LocalDateTime dueDateAt) {
        return taskGateway.create(Task.aNew(description, dueDateAt));
    }

    public List<Task> findAllTasks() {
        return taskGateway.findAll();
    }

    public void updateTaskStatus(Long id, TaskStatus status) {
        Task task = findTaskById(id);
        Task updatedStatusTask = task.updateStatus(status);
        taskGateway.update(updatedStatusTask);
    }

    public void deleteTask(Long id) {
        validateTaskExists(id);
        taskGateway.delete(id);
    }

    public Task findTaskById(Long id) {
        return taskGateway.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TASK_NOT_FOUND));
    }

    private void validateTaskExists(Long id) {
        findTaskById(id);
    }
}
