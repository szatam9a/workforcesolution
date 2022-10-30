package dev.workforce.workforce.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CreateJobCommand {

    @NotBlank(message = "Invalid apikey")
    @Schema(description = "Client apikey to authenticate the clients", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String apiKey;
    @Size(max = 50, message = "title cant be longer than 50 character")
    @Schema(description = "The title of the position", example = "java developer")
    private String title;
    @Size(max = 50, message = " location cant be longer than 50 character")
    @Schema(description = "The locations of the position", example = "Budapest")
    private String location;
}
