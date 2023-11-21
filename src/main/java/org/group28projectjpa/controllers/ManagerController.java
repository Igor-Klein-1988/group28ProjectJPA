package org.group28projectjpa.controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.group28projectjpa.core.services.ManagerService;
import org.group28projectjpa.dto.manager.ManagerCreateRequestDTO;
import org.group28projectjpa.dto.manager.ManagerCreateResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping
    public ResponseEntity<ManagerCreateResponseDTO> createNewManager(@Valid @RequestBody ManagerCreateRequestDTO request) {
        return new ResponseEntity<>(managerService.createManager(request), HttpStatus.CREATED);
    }
}
