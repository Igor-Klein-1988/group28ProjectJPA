package org.group28projectjpa.dto.manager;

import lombok.Data;

@Data
public class ManagerCreateRequestDTO {

    private String managerName;
    private String password;
    private String email;

}
