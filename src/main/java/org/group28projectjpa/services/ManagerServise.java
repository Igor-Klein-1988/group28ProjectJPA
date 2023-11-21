package org.group28projectjpa.services;

import org.group28projectjpa.dto.manager.ManagerCreateRequestDTO;
import org.group28projectjpa.dto.manager.ManagerCreateResponseDTO;
import org.group28projectjpa.dto.manager.ManagerResponseDTO;
import org.group28projectjpa.dto.task.TaskCreateOrUpdateResponseDTO;
import org.group28projectjpa.dto.task.TaskResponseDTO;

import java.util.List;

public interface ManagerServise {
    ManagerCreateResponseDTO addManager(ManagerCreateRequestDTO newManager);

    List<ManagerResponseDTO> getAllManagers();

    ManagerResponseDTO getManagerByEmail(String email);

    void deleteManager(Integer id);

    List<TaskCreateOrUpdateResponseDTO> getAllTasksByManager();
}
