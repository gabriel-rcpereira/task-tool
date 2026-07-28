package com.grcp.demo.task.tool.core.gateway;

import com.grcp.demo.task.tool.core.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskGateway {

    Task create(Task task);

    Task update(Task task);

    Optional<Task> findById(Long id);

    List<Task> findAll();

    void delete(Long id);
}
