package org.group28projectjpa.dto.manager;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.group28projectjpa.entity.Manager;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ManagerCreateResponseDTO", description = "Data Transfer Object representing the details of a manager.")
public class ManagerCreateResponseDTO {

    private Integer id;
    private String managerName;
    private String email;
    private LocalDateTime createManagerDate;
    private LocalDateTime lastUpdate;

    public static ManagerCreateResponseDTO from(Manager manager) {

        return ManagerCreateResponseDTO.builder()
                .id(manager.getId())
                .managerName(manager.getManagerName())
                .email(manager.getEmail())
                .createManagerDate(manager.getCreateManagerDate())
                .lastUpdate(manager.getLastUpdate())
                .build();
    }
}
