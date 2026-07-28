package com.grcp.demo.task.tool.external.db.repository;

import com.grcp.demo.task.tool.external.db.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
}
