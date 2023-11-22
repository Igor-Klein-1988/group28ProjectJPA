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

    @Schema(description = "Unique identifier for the manager", example = "1")
    private Integer id;
    @Schema(description = "Manager's name", example = "John")
    private String managerName;
    @Schema(description = "Manager's email", example = "manager@mail.com")
    private String email;
    @Schema(description = "Date and time when the manager was created", example = "2023-11-22T10:30:00")
    private LocalDateTime createManagerDate;
    @Schema(description = "Date and time of the last update", example = "2023-11-30T12:45:00")
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
