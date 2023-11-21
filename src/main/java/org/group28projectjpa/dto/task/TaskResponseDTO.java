package org.group28projectjpa.dto.task;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Schema(name = "TaskResponse", description = "Data Transfer Object representing the details of a task.")
public class TaskResponseDTO {

    private Integer id;
    private String taskName;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private LocalDateTime deadline;
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
