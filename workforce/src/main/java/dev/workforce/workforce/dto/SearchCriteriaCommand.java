package dev.workforce.workforce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

@Data
@AllArgsConstructor
public class SearchCriteriaCommand {

    @NotBlank(message = "Invalid apikey")
    @Schema(description = "Client apikey to authenticate the clients", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String apiKey;
    @Size(max = 50, message = "Search keyword cant be longer than 50 character")
    @Schema(description = "A keyword to find it in positions title" ,example = "java")
    private String keyWord;
    @Size(max = 50, message = "Location cant be longer than 50 character")
    @Schema(description = "The location of the position" ,example = "Budapest")
    private String location;
}
