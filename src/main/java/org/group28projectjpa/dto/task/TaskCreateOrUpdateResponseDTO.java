package org.group28projectjpa.dto.task;

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
public class TaskCreateOrUpdateResponseDTO {

    private Integer id;
    private String taskName;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private LocalDateTime deadline;
    private TaskStatus status;
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
