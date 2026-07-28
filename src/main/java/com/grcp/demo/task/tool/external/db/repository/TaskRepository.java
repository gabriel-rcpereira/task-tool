package com.grcp.demo.task.tool.external.db.repository;

import com.grcp.demo.task.tool.external.db.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ListCrudRepository<TaskEntity, Long>, PagingAndSortingRepository<TaskEntity, Long> {

    Page<TaskEntity> findByStatus(String status, Pageable pageable);
}
