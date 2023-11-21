package org.group28projectjpa.dto.manager;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagerResponseDTO {

    private Integer id;
    private String managerName;
    private String email;

}
