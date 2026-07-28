package com.grcp.demo.task.tool;

import com.grcp.demo.task.tool.external.db.entity.TaskEntity;
import com.grcp.demo.task.tool.external.db.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskPersistenceIntegrationTest extends PostgresIntegrationTestSupport {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
    }

    @Test
    void shouldPersistCreatedTaskInPostgresql() throws Exception {
        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "description": "Persisted task",
                                  "dueDateAt": "2026-08-05T10:15:00"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.description").value("Persisted task"))
                .andExpect(jsonPath("$.status").value("TODO"));

        List<TaskEntity> persistedTasks = taskRepository.findAll();

        assertThat(persistedTasks).hasSize(1);
        TaskEntity persistedTask = persistedTasks.getFirst();
        assertThat(persistedTask.getDescription()).isEqualTo("Persisted task");
        assertThat(persistedTask.getStatus()).isEqualTo("TODO");
        assertThat(persistedTask.getDueDateAt()).isNotNull();
        assertThat(persistedTask.getCreatedAt()).isNotNull();
        assertThat(persistedTask.getUpdatedAt()).isNotNull();
    }
}
