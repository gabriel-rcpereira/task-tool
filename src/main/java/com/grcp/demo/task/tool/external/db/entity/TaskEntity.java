package com.grcp.demo.task.tool.external.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    private Long id;
    private String description;
    private LocalDateTime dueDateAt;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TaskEntity() {
        // default constructor
    }

    public TaskEntity(
            Long id,
            String description,
            LocalDateTime dueDateAt,
            String status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.dueDateAt = dueDateAt;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDateAt() {
        return dueDateAt;
    }

    public void setDueDateAt(LocalDateTime dueDateAt) {
        this.dueDateAt = dueDateAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
