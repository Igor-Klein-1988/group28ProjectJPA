package org.group28projectjpa.services.impl;

import lombok.RequiredArgsConstructor;
import org.group28projectjpa.dto.manager.ManagerCreateRequestDTO;
import org.group28projectjpa.dto.manager.ManagerCreateResponseDTO;
import org.group28projectjpa.dto.manager.ManagerResponseDTO;
import org.group28projectjpa.dto.task.TaskCreateOrUpdateResponseDTO;
import org.group28projectjpa.entity.Manager;
import org.group28projectjpa.entity.Task;
import org.group28projectjpa.exceptions.RestException;
import org.group28projectjpa.repository.ManagerRepository;
import org.group28projectjpa.repository.TaskRepository;
import org.group28projectjpa.services.ManagerServise;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiseImpl implements ManagerServise {
    public final ManagerRepository managerRepository;
    public final TaskRepository taskRepository;

    @Override
    public ManagerCreateResponseDTO addManager(ManagerCreateRequestDTO newManager) {
        checkEmail(newManager.getEmail());

        Manager manager = Manager.builder()
                .managerName(newManager.getManagerName())
                .password(newManager.getPassword())
                .email(newManager.getEmail())
                .createManagerDate(LocalDateTime.now())
                .lastUpdate(LocalDateTime.now())
                .build();

        managerRepository.save(manager);

        return ManagerCreateResponseDTO.from(manager);
    }

    @Override
    public List<ManagerResponseDTO> getAllManagers() {
        return ManagerResponseDTO.from(managerRepository.findAll());
    }

    @Override
    public ManagerResponseDTO getManagerByEmail(String email) {
        Manager manager = managerRepository.findByEmail(email).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                "Manager with Email: <" + email + "> not found"));
        return ManagerResponseDTO.from(manager);
    }

    @Override
    public void deleteManager(Integer id) {
        Manager manager = managerRepository.findById(id).orElseThrow(
                () -> new RestException(HttpStatus.CONFLICT,
                        "Manager with ID: <" + id + "> not found"));

        managerRepository.delete(manager);
    }

    @Override
    public List<TaskCreateOrUpdateResponseDTO> getAllTasksByManager() {
        List<Task> tasks = taskRepository.findAll();

        return TaskCreateOrUpdateResponseDTO.from(tasks);
    }


    // ----
    private void checkEmail(String email) {
        if (managerRepository.findByEmail(email).isPresent()) {
            throw new RestException(HttpStatus.CONFLICT, "Manager with Email: <" + email + "> already exists");
        }
    }
}
