package dev.workforce.workforce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    @Schema(description = "The title of the position", example = "java developer")
    private String title;
    @Schema(description = "The locations of the position", example = "Budapest")
    private String location;
}
