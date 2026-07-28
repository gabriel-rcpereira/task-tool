package com.grcp.demo.task.tool;

import com.grcp.demo.task.tool.external.db.entity.TaskEntity;
import com.grcp.demo.task.tool.external.db.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskListingIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();

        taskRepository.save(new TaskEntity(
                1L,
                "old todo",
                LocalDateTime.of(2026, 8, 1, 9, 0),
                "TODO",
                LocalDateTime.of(2026, 7, 20, 10, 0),
                LocalDateTime.of(2026, 7, 20, 10, 0)));
        taskRepository.save(new TaskEntity(
                2L,
                "in progress",
                LocalDateTime.of(2026, 8, 2, 9, 0),
                "IN_PROGRESS",
                LocalDateTime.of(2026, 7, 21, 10, 0),
                LocalDateTime.of(2026, 7, 21, 10, 0)));
        taskRepository.save(new TaskEntity(
                3L,
                "new todo",
                LocalDateTime.of(2026, 8, 3, 9, 0),
                "TODO",
                LocalDateTime.of(2026, 7, 22, 10, 0),
                LocalDateTime.of(2026, 7, 22, 10, 0)));
    }

    @Test
    void shouldReturnDefaultPaginatedTasksOrderedDeterministically() throws Exception {
        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(20))
                .andExpect(jsonPath("$.totalItems").value(3))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.items.length()").value(3))
                .andExpect(jsonPath("$.items[0].id").value(3))
                .andExpect(jsonPath("$.items[1].id").value(2))
                .andExpect(jsonPath("$.items[2].id").value(1));
    }

    @Test
    void shouldReturnRequestedPageAndSize() throws Exception {
        mockMvc.perform(get("/api/v1/tasks")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(2))
                .andExpect(jsonPath("$.totalItems").value(3))
                .andExpect(jsonPath("$.totalPages").value(2))
                .andExpect(jsonPath("$.items.length()").value(2))
                .andExpect(jsonPath("$.items[0].id").value(3))
                .andExpect(jsonPath("$.items[1].id").value(2));
    }

    @Test
    void shouldFilterTasksByStatus() throws Exception {
        mockMvc.perform(get("/api/v1/tasks")
                        .param("status", "TODO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalItems").value(2))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.items.length()").value(2))
                .andExpect(jsonPath("$.items[0].status").value("TODO"))
                .andExpect(jsonPath("$.items[1].status").value("TODO"))
                .andExpect(jsonPath("$.items[0].id").value(3))
                .andExpect(jsonPath("$.items[1].id").value(1));
    }

    @Test
    void shouldRejectInvalidPaginationInputs() throws Exception {
        mockMvc.perform(get("/api/v1/tasks")
                        .param("page", "-1")
                        .param("size", "0"))
                .andExpect(status().isBadRequest());
    }
}
