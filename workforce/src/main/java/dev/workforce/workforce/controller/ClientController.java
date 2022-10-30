package dev.workforce.workforce.controller;

import dev.workforce.workforce.dto.CreateClientCommand;
import dev.workforce.workforce.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/client")
@Validated
public class ClientController {
    private ClientService clientService;

    @PostMapping
    @Operation(summary= "Create a client and return with an apikey to authorize client")
    public UUID createNewClient(@Valid @RequestBody CreateClientCommand createClientCommand) {
        return clientService.createNewClient(createClientCommand);
    }
}
