package com.grcp.demo.task.tool.external.db.impl;

import com.grcp.demo.task.tool.core.gateway.TaskGateway;
import com.grcp.demo.task.tool.core.model.Task;
import com.grcp.demo.task.tool.core.model.TaskStatus;
import com.grcp.demo.task.tool.external.db.entity.TaskEntity;
import com.grcp.demo.task.tool.external.db.repository.TaskRepository;
import io.hypersistence.tsid.TSID;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class TaskGatewayImpl implements TaskGateway {

    private final TaskRepository taskRepository;

    public TaskGatewayImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        TaskEntity newTaskEntity = new TaskEntity(
                TSID.Factory.getTsid().toLong(),
                task.description(),
                task.dueDateAt(),
                task.status().name(),
                task.createdAt(),
                task.updatedAt());
        TaskEntity savedTaskEntity = taskRepository.save(newTaskEntity);
        return toTask(savedTaskEntity);
    }

    @Override
    public Task update(Task task) {
        TaskEntity taskEntity = new TaskEntity(
                task.id(),
                task.description(),
                task.dueDateAt(),
                task.status().name(),
                task.createdAt(),
                task.updatedAt());
        TaskEntity savedTaskEntity = taskRepository.save(taskEntity);
        return toTask(savedTaskEntity);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id)
                .map(TaskGatewayImpl::toTask);
    }

    @Override
    public List<Task> findAll() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .map(TaskGatewayImpl::toTask)
                .toList();
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    private static Task toTask(TaskEntity taskEntity) {
        return new Task(
                taskEntity.getId(),
                taskEntity.getDescription(),
                taskEntity.getDueDateAt(),
                TaskStatus.valueOf(taskEntity.getStatus()),
                taskEntity.getCreatedAt(),
                taskEntity.getUpdatedAt());
    }
}
