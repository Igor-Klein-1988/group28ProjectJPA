package org.group28projectjpa.dto.manager;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "ManagerCreateRequestDTO", description = "Data for creating a manager")
public class ManagerCreateRequestDTO {

    @Schema(description = "Manager's name", example = "John")
    private String managerName;
    @Schema(description = "Manager's password", example = "Password@123")
    private String password;
    @Schema(description = "Manager's email", example = "manager@mail.com")
    private String email;

}
