package org.group28projectjpa.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.group28projectjpa.dto.manager.ManagerCreateResponseDTO;
import org.group28projectjpa.entity.TaskStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "TaskUpdateRequestDTO", description = "Data Transfer Object representing the details of a task.")
public class TaskUpdateRequestDTO {
    @Schema(description = "Unique identifier for the task", example = "1")
    private Integer id;

    @Schema(description = "Task name", example = "Complete Project")
    private String taskName;

    @Schema(description = "Task description", example = "Finish all project tasks by the deadline.")
    private String description;

    @Schema(description = "Deadline for completing the task", example = "2023-12-01T18:00:00")
    private LocalDateTime deadline;

    @Schema(description = "Status of the task (e.g., Open / Close / OnHold)", example = "Open")
    private TaskStatus status;

    @Schema(description = "Manager's name", example = "John")
    private String managerName;
}
