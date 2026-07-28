package com.grcp.demo.task.tool.external.db.impl;

import com.grcp.demo.task.tool.core.gateway.TaskGateway;
import com.grcp.demo.task.tool.core.model.Task;
import com.grcp.demo.task.tool.core.model.TaskPage;
import com.grcp.demo.task.tool.core.model.TaskStatus;
import com.grcp.demo.task.tool.external.db.entity.TaskEntity;
import com.grcp.demo.task.tool.external.db.repository.TaskRepository;
import io.hypersistence.tsid.TSID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskGatewayImpl implements TaskGateway {

    private static final Sort TASK_LIST_SORT = Sort.by(
            Sort.Order.desc("createdAt"),
            Sort.Order.desc("id"));

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
    public TaskPage findAll(int page, int size, TaskStatus status) {
        Pageable pageable = PageRequest.of(page, size, TASK_LIST_SORT);
        Page<TaskEntity> taskPage = status == null
                ? taskRepository.findAll(pageable)
                : taskRepository.findByStatus(status.name(), pageable);

        return new TaskPage(
                taskPage.getContent().stream()
                        .map(TaskGatewayImpl::toTask)
                        .toList(),
                taskPage.getNumber(),
                taskPage.getSize(),
                taskPage.getTotalElements(),
                taskPage.getTotalPages());
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
