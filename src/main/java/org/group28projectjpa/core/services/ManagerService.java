package org.group28projectjpa.core.services;


import org.group28projectjpa.core.utils.ManagerConverter;
import org.group28projectjpa.core.validation.IsAlreadyExistException;
import org.group28projectjpa.core.validation.NotFoundException;
import org.group28projectjpa.dto.manager.ManagerCreateRequestDTO;
import org.group28projectjpa.dto.manager.ManagerCreateResponseDTO;
import org.group28projectjpa.dto.manager.ManagerResponseDTO;
import org.group28projectjpa.entity.Manager;
import org.group28projectjpa.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManagerService {

    private final ManagerRepository repository;
    private final ManagerConverter converter;


    public ManagerService(ManagerRepository repository, ManagerConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    // Получить список всех пользователей
    public List<ManagerResponseDTO> findAllManagers() {
        return repository.findAll().stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    // Найти пользователя по имени
    public ManagerResponseDTO findByManagerName(String managerName) {
        Manager manager = repository.findByManagerName(managerName).orElseThrow(() -> new NotFoundException("Manager not found with name: " + managerName));
        return converter.toDto(manager);
    }

    // Найти пользователя по имени
    public Manager findByManagerNameForCreateTask(String managerName) {
        Manager manager = repository.findByManagerName(managerName).orElseThrow(() -> new NotFoundException("Manager not found with name: " + managerName));
        return manager;
    }

    // Создать нового пользователя

    public ManagerCreateResponseDTO createManager(ManagerCreateRequestDTO requestDTO) {
        if (repository.findByManagerName(requestDTO.getManagerName()).isEmpty()) {
            Manager newManager = converter.fromDto(requestDTO);

            Manager savedManager = repository.save(newManager);
            return converter.toCreateDto(savedManager);
        } else {
            throw new IsAlreadyExistException("Manager with name " + requestDTO.getManagerName() + " is already exist.");
        }
    }

    // Удалить пользователя

    public void deleteManager(Integer id) {
        Manager manager = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manager is not found with ID: " + id));
        repository.delete(manager);
    }

}
