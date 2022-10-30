package dev.workforce.workforce.dto;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CreateClientCommand {

    @Size(max = 100, message = "Name cant be longer than 100 character")
    @Schema(description = "The name if the client", example = "Minrosoft")
    private String name;
    @Email(message = "Email address is invalid")
    @Schema(description = "The email address of the client", example = "Minrosoft@slowmail.org")
    private String emailAddress;
}
