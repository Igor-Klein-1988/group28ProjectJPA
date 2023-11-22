package org.group28projectjpa.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.group28projectjpa.dto.manager.ManagerCreateResponseDTO;
import org.group28projectjpa.entity.Manager;
import org.group28projectjpa.entity.Task;
import org.group28projectjpa.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "TaskCreateOrUpdateResponseDTO", description = "Data Transfer Object representing the details of a task.")
public class TaskCreateOrUpdateResponseDTO {

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

    @Schema(description = "Details of the manager associated with the task")
    private ManagerCreateResponseDTO managerCreateResponseDTO;


    public static TaskCreateOrUpdateResponseDTO from(Task task) {
        Manager manager = task.getManager();
        ManagerCreateResponseDTO managerCreateResponseDTO = ManagerCreateResponseDTO.builder()
                .id(manager.getId())
                .managerName(manager.getManagerName())
                .createManagerDate(manager.getCreateManagerDate())
                .lastUpdate(manager.getLastUpdate())
                .build();

        return TaskCreateOrUpdateResponseDTO.builder()
                .id(task.getId())
                .taskName(task.getTaskName())
                .description(task.getDescription())
                .createDate(task.getCreateDate())
                .lastUpdate(task.getLastUpdate())
                .deadline(task.getDeadline())
                .status(task.getStatus())
                .managerCreateResponseDTO(managerCreateResponseDTO)
                .build();
    }

    public static List<TaskCreateOrUpdateResponseDTO> from(List<Task> tasks) {
        return tasks.stream()
                .map(TaskCreateOrUpdateResponseDTO::from)
                .toList();
    }
}
