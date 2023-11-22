package org.group28projectjpa.controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.group28projectjpa.core.services.ManagerService;
import org.group28projectjpa.domain.dto.manager.ManagerCreateRequestDTO;
import org.group28projectjpa.domain.dto.manager.ManagerCreateResponseDTO;
import org.group28projectjpa.domain.dto.manager.ManagerResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping
    public ResponseEntity<ManagerCreateResponseDTO> createNewManager(@Valid @RequestBody ManagerCreateRequestDTO request) {
        return new ResponseEntity<>(managerService.createManager(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ManagerResponseDTO>> findAllManager() {
        return new ResponseEntity<>(managerService.findAllManagers(), HttpStatus.OK);
    }
}
