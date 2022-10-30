package dev.workforce.workforce.controller;

import dev.workforce.workforce.dto.CreateJobCommand;
import dev.workforce.workforce.dto.JobDto;
import dev.workforce.workforce.dto.SearchCriteriaCommand;
import dev.workforce.workforce.service.ClientService;
import dev.workforce.workforce.service.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/position")
@Validated
public class PositionController {
    private PositionService positionService;
    private ClientService clientService;

    @Operation(summary = "Create new position and save it to the database")
    @PostMapping
    public URI createNewJob(@Valid @RequestBody CreateJobCommand createJobCommand) {
        clientService.validateApiKey(createJobCommand.getApiKey());
        return positionService.createNewPosition(createJobCommand);
    }

    @Operation(summary = "find and return with a position by it's id")
    @GetMapping("/{id}")
    public JobDto findJobById(@PathVariable Long id) {
        return positionService.findPositionById(id);
    }

    @Operation(summary = "by a keyword and a location find all the fitting positions url")
    @GetMapping("/search")
    public List<URI> findJobWithRequirements(
            @RequestParam @Size(max = 50, message = "keyword cant be longer than 50 character") String keyword,
            @RequestParam @Size(max = 50, message = "location cant be longer than 50 character") String location,
            @RequestParam String apikey) {
        clientService.validateApiKey(apikey);
        return positionService.findPositionWhereFitsToCriterias(keyword, location);

    }
}
