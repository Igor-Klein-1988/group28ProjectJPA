package org.group28projectjpa.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.group28projectjpa.dto.StandardResponseDto;
import org.group28projectjpa.dto.manager.ManagerCreateRequestDTO;
import org.group28projectjpa.dto.manager.ManagerCreateResponseDTO;
import org.group28projectjpa.dto.manager.ManagerResponseDTO;
import org.group28projectjpa.dto.task.TaskCreateOrUpdateResponseDTO;
import org.group28projectjpa.dto.task.TaskResponseDTO;
import org.group28projectjpa.services.ManagerServise;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tags(value = {@Tag(name = "Managers")})
public class ManagerController {
    private final ManagerServise managerServise;

    @PostMapping("/managers")
    @Operation(summary = "Create manager", description = "Available to all users.")
    public ResponseEntity<ManagerCreateResponseDTO> addManager(@RequestBody ManagerCreateRequestDTO newManager) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(managerServise.addManager(newManager));
    }

    @GetMapping("/admin/managers/")
    @Operation(summary = "Get All Managers", description = "Retrieves a list of all managers. Available to Admin.")
    public ResponseEntity<List<ManagerResponseDTO>> getAllManagers() {
        List<ManagerResponseDTO> managers = managerServise.getAllManagers();
        return managers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(managers);

    }

    @GetMapping("/admin/managers/{email}")
    @Operation(summary = "Get by Email", description = "Available to Admin.")
    public ResponseEntity<ManagerResponseDTO> getManagerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(managerServise.getManagerByEmail(email));

    }

    @DeleteMapping("/admin/managers/{id}")
    @Operation(summary = "Delete Manager", description = "Available to Admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Manager deleted successfully. No content.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ManagerResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Manager not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    public ResponseEntity<Void> deleteManager(@PathVariable("id") Integer id) {
        managerServise.deleteManager(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/tasks")
    public ResponseEntity<List<TaskCreateOrUpdateResponseDTO>> getAllTasksByManager() {
        List<TaskCreateOrUpdateResponseDTO> managers = managerServise.getAllTasksByManager();
        return managers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(managers);

    }
}
