package com.grcp.demo.task.tool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskApiDocumentationIntegrationTest extends PostgresIntegrationTestSupport {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldExposeOpenApiSpecificationForTaskEndpoints() throws Exception {
        mockMvc.perform(get("/v3/api-docs").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/api/v1/tasks']").exists())
                .andExpect(jsonPath("$.paths['/api/v1/tasks/{id}']").exists())
                .andExpect(jsonPath("$.paths['/api/v1/tasks/{id}/status/{status}']").exists())
                .andExpect(jsonPath("$.components.schemas.TaskRequest").exists())
                .andExpect(jsonPath("$.components.schemas.TaskResponse").exists())
                .andExpect(jsonPath("$.components.schemas.TaskPageResponse").exists())
                .andExpect(jsonPath("$.components.schemas.TaskResponse.properties.status.description").value("Current lifecycle status for the task."))
                .andExpect(jsonPath("$.components.schemas.TaskResponse.properties.status.enum", hasItems(
                        "TODO",
                        "IN_PROGRESS",
                        "DONE",
                        "CANCELLED")))
                .andExpect(jsonPath("$.paths['/api/v1/tasks'].get.parameters[2].description").value("Optional status filter."))
                .andExpect(jsonPath("$.paths['/api/v1/tasks'].get.parameters[2].schema.description").value("Lifecycle status for a task."))
                .andExpect(jsonPath("$.paths['/api/v1/tasks'].get.parameters[2].schema.enum", hasItems(
                        "TODO",
                        "IN_PROGRESS",
                        "DONE",
                        "CANCELLED")))
                .andExpect(jsonPath("$.paths['/api/v1/tasks/{id}/status/{status}'].patch.parameters[1].description").value("New task status."))
                .andExpect(jsonPath("$.paths['/api/v1/tasks/{id}/status/{status}'].patch.parameters[1].schema.description").value("Lifecycle status for a task."))
                .andExpect(jsonPath("$.paths['/api/v1/tasks/{id}/status/{status}'].patch.parameters[1].schema.enum", hasItems(
                        "TODO",
                        "IN_PROGRESS",
                        "DONE",
                        "CANCELLED")));
    }

    @Test
    void shouldExposeSwaggerUiForInteractiveApiExploration() throws Exception {
        mockMvc.perform(get("/swagger-ui.html"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/swagger-ui/index.html"));
    }
}
