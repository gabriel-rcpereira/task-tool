package com.grcp.demo.task.tool.core.gateway;

import com.grcp.demo.task.tool.core.model.Task;
import com.grcp.demo.task.tool.core.model.TaskPage;
import com.grcp.demo.task.tool.core.model.TaskStatus;

import java.util.Optional;

public interface TaskGateway {

    Task create(Task task);

    Task update(Task task);

    Optional<Task> findById(Long id);

    TaskPage findAll(int page, int size, TaskStatus status);

    void delete(Long id);
}
