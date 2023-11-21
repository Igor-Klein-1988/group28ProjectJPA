package org.group28projectjpa.core.utils;


import org.group28projectjpa.dto.manager.ManagerCreateRequestDTO;
import org.group28projectjpa.dto.manager.ManagerCreateResponseDTO;
import org.group28projectjpa.dto.manager.ManagerResponseDTO;
import org.group28projectjpa.entity.Manager;
import org.springframework.stereotype.Service;


@Service
public class ManagerConverter {

    public ManagerCreateResponseDTO toCreateDto(Manager manager) {
        return new ManagerCreateResponseDTO(manager.getId(), manager.getManagerName());
    }

    public ManagerResponseDTO toDto(Manager manager) {
        return new ManagerResponseDTO(manager.getId(), manager.getManagerName(), manager.getEmail());
    }

    public Manager fromDto(ManagerCreateRequestDTO dto) {
        Manager newManager = new Manager();
        if (dto.getManagerName() != null) {
            newManager.setManagerName(dto.getManagerName());
        }
        if (dto.getPassword() != null) {
            newManager.setPassword(dto.getPassword());
        }
        if (dto.getEmail() != null) {
            newManager.setEmail(dto.getEmail());
        }

        return newManager;
    }
}
