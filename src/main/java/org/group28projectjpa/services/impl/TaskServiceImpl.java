package org.group28projectjpa.services.impl;

import lombok.RequiredArgsConstructor;
import org.group28projectjpa.dto.task.TaskCreateOrUpdateResponseDTO;
import org.group28projectjpa.dto.task.TaskCreateRequestDTO;
import org.group28projectjpa.dto.task.TaskResponseDTO;
import org.group28projectjpa.dto.task.TaskUpdateRequestDTO;
import org.group28projectjpa.entity.Manager;
import org.group28projectjpa.entity.Task;
import org.group28projectjpa.entity.TaskStatus;
import org.group28projectjpa.exceptions.RestException;
import org.group28projectjpa.repository.ManagerRepository;
import org.group28projectjpa.repository.TaskRepository;
import org.group28projectjpa.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ManagerRepository managerRepository;

    @Override
    public List<TaskResponseDTO> getTasks() {
        return TaskResponseDTO.from(taskRepository.findAll());
    }

    @Override
    public TaskResponseDTO getTask(Integer id) {
        Task task = getTaskOrElseThrow(id);

        return TaskResponseDTO.from(task);
    }


    @Override
    public TaskCreateOrUpdateResponseDTO addTask(TaskCreateRequestDTO newTask) {
        Manager manager = getManagerOrElseThrow(newTask.getManagerName());

        Task task = Task.builder()
                .taskName(newTask.getTaskName())
                .description(newTask.getDescription())
                .createDate(LocalDateTime.now())
                .lastUpdate(LocalDateTime.now())
                .deadline(newTask.getDeadline())
                .status(TaskStatus.Open)
                .manager(manager)
                .build();

        taskRepository.save(task);

        return TaskCreateOrUpdateResponseDTO.from(task);
    }

    @Override
    public TaskCreateOrUpdateResponseDTO updateTask(TaskUpdateRequestDTO updateTask) {
        Task taskForUpdate = getTaskOrElseThrow(updateTask.getId());
        Manager manager = getManagerOrElseThrow(updateTask.getManagerName());

        taskForUpdate.setTaskName(updateTask.getTaskName());
        taskForUpdate.setDescription(updateTask.getDescription());
        taskForUpdate.setDeadline(updateTask.getDeadline());
        taskForUpdate.setStatus(updateTask.getStatus());
        taskForUpdate.setManager(manager);

        taskRepository.save(taskForUpdate);

        return TaskCreateOrUpdateResponseDTO.from(taskForUpdate);

    }

    @Override
    public void deleteTask(Integer id) {
        Task taskForDelete = getTaskOrElseThrow(id);

        taskRepository.delete(taskForDelete);
    }


    //    ----

    private Manager getManagerOrElseThrow(String updateTask) {
        return managerRepository.findByManagerName(updateTask).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND,
                        "Manager with name <" + updateTask + "> not found"));
    }

    private Task getTaskOrElseThrow(Integer id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "Task with id <" + id + "> not found"));
    }
}
