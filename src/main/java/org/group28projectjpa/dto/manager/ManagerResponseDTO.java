package org.group28projectjpa.dto.manager;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.group28projectjpa.entity.Manager;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerResponseDTO {

    private Integer id;
    private String managerName;
    private String email;
    private LocalDateTime createManagerDate;
    private LocalDateTime lastUpdate;

    public static ManagerResponseDTO from(Manager manager) {
        return ManagerResponseDTO.builder()
                .id(manager.getId())
                .managerName(manager.getManagerName())
                .email(manager.getEmail())
                .createManagerDate(manager.getCreateManagerDate())
                .lastUpdate(manager.getLastUpdate())
                .build();

    }

    public static List<ManagerResponseDTO> from(List<Manager> managers) {
        return managers.stream()
                .map(ManagerResponseDTO::from)
                .toList();
    }
}
