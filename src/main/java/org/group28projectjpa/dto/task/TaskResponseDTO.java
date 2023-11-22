package org.group28projectjpa.dto.task;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.group28projectjpa.dto.manager.ManagerCreateResponseDTO;
import org.group28projectjpa.entity.Task;
import org.group28projectjpa.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "TaskResponseDTO", description = "Data Transfer Object representing the details of a task.")
public class TaskResponseDTO {
    @Schema(description = "Unique identifier for the task", example = "1")
    private Integer id;

    @Schema(description = "Task name", example = "Complete Project")
    private String taskName;

    @Schema(description = "Task description", example = "Finish all project tasks by the deadline.")
    private String description;

    @Schema(description = "Date and time when the task was created", example = "2023-11-22T10:30:00")
    private LocalDateTime createDate;

    @Schema(description = "Date and time of the last update", example = "2023-11-30T12:45:00")
    private LocalDateTime lastUpdate;

    @Schema(description = "Deadline for completing the task", example = "2023-12-01T18:00:00")
    private LocalDateTime deadline;

    @Schema(description = "Status of the task (e.g., Open / Close / OnHold)", example = "Open")
    private TaskStatus status;

    public static TaskResponseDTO from(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .taskName(task.getTaskName())
                .description(task.getDescription())
                .createDate(task.getCreateDate())
                .lastUpdate(task.getLastUpdate())
                .deadline(task.getDeadline())
                .status(task.getStatus())
                .build();
    }

    public static List<TaskResponseDTO> from(List<Task> tasks) {
        return tasks
                .stream()
                .map(TaskResponseDTO::from)
                .toList();
    }
}
