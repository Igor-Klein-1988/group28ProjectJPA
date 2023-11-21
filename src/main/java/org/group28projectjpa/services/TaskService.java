package org.group28projectjpa.services;

import org.group28projectjpa.dto.task.TaskCreateOrUpdateResponseDTO;
import org.group28projectjpa.dto.task.TaskCreateRequestDTO;
import org.group28projectjpa.dto.task.TaskResponseDTO;
import org.group28projectjpa.dto.task.TaskUpdateRequestDTO;

import java.net.URI;
import java.util.List;

public interface TaskService  {
    List<TaskResponseDTO> getTasks();

    TaskResponseDTO getTask(Integer id);

    TaskCreateOrUpdateResponseDTO addTask(TaskCreateRequestDTO newTask);

    TaskCreateOrUpdateResponseDTO updateTask(TaskUpdateRequestDTO updateTask);

    void deleteTask(Integer id);

}
