package org.group28projectjpa.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.group28projectjpa.dto.StandardResponseDto;
import org.group28projectjpa.dto.task.TaskCreateOrUpdateResponseDTO;
import org.group28projectjpa.dto.task.TaskCreateRequestDTO;
import org.group28projectjpa.dto.task.TaskResponseDTO;
import org.group28projectjpa.dto.task.TaskUpdateRequestDTO;
import org.group28projectjpa.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
@Tags(value = {@Tag(name = "Tasks")})
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Get All Tasks", description = "Retrieves a list of all tasks. Available to all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved the list of tasks",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponseDTO.class))
            ),
            @ApiResponse(responseCode = "204",
                    description = "No content - No tasks found",
                    content = @Content)
    })
    public ResponseEntity<List<TaskResponseDTO>> getTasks() {
        List<TaskResponseDTO> tasks = taskService.getTasks();
        return tasks.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tasks);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task", description = "Available to all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Request processed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Task not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    public ResponseEntity<TaskResponseDTO> getTask(@Parameter(description = "task id", example = "1")
                                                   @PathVariable Integer id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PostMapping
    @Operation(summary = "Create task", description = "Available only to authenticated users.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Task successfully created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Manager not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    public ResponseEntity<TaskCreateOrUpdateResponseDTO> addTask(@RequestBody TaskCreateRequestDTO newTask) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.addTask(newTask));
    }

    @PutMapping
    @Operation(summary = "Update task", description = "Available only to authenticated users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Request processed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskCreateOrUpdateResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Task or Manager not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    public ResponseEntity<TaskCreateOrUpdateResponseDTO> updateTask(@RequestBody TaskUpdateRequestDTO updateTask) {
        return ResponseEntity
                .ok(taskService.updateTask(updateTask));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task", description = "Available only to authenticated users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Task deleted successfully. No content.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskCreateOrUpdateResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Task not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
