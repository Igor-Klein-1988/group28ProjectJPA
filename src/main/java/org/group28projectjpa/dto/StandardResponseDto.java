package org.group28projectjpa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Message", description = "A standard response message from the server, possibly indicating an error, " +
        "a state change, etc.")
public class StandardResponseDto {
    @Schema(description = "The response message, e.g., an error message, state change notification, etc.",
            example = "Task with id 55 not found")
    private String message;

    @Schema(description = "The timestamp when the response is generated", example = "2023-11-21T15:30:00")
    private LocalDateTime timestamp;
}
