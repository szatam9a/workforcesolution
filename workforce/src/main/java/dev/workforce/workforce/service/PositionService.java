package dev.workforce.workforce.service;

import dev.workforce.workforce.dto.CreateJobCommand;
import dev.workforce.workforce.dto.JobDto;
import dev.workforce.workforce.dto.SearchCriteriaCommand;
import dev.workforce.workforce.entity.Client;
import dev.workforce.workforce.entity.Position;
import dev.workforce.workforce.exception.JobNotFoundException;
import dev.workforce.workforce.exception.NoJobWasFoundException;
import dev.workforce.workforce.repository.PositionRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PositionService {
    private PositionRepository positionRepository;

    public URI createNewPosition(CreateJobCommand createJobCommand) {
        Position position = new Position();
        position.setLocation(createJobCommand.getLocation());
        position.setTitle(createJobCommand.getTitle());
        return URI.create("http:/localhost:8080/api/position/" + positionRepository.save(position).getId());
    }

    public List<URI> findPositionWhereFitsToCriterias(String keyword, String location) {
        List<Position> positions = positionRepository.findByTitleContainingIgnoreCaseAndLocationIgnoreCase(keyword, location);
        if (positions.isEmpty()) {
            throw new NoJobWasFoundException();
        }
        List<URI> uriList = positions.stream().map(e -> URI.create("api/position/" + e.getId())).collect(Collectors.toList());
        return uriList;
    }

    public JobDto findPositionById(Long id) {
        Position position = positionRepository.findById(id).orElseThrow(() -> new JobNotFoundException(id));
        JobDto jobDto = new JobDto();
        jobDto.setLocation(position.getLocation());
        jobDto.setTitle(position.getTitle());
        return jobDto;
    }

    private void persistPositionDataToDB(String title, String location) {
        Position position = new Position();
        position.setLocation(location);
        position.setTitle(title);
        positionRepository.save(position);
    }

    @PostConstruct
    public void initPositionData() {
        Path path = Path.of("workforce/src/main/resources/initiatedatapositions.csv");
        List<String> linesOfFile = new LinkedList<>();
        try (BufferedReader reader = Files.newBufferedReader(path.toAbsolutePath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                linesOfFile.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String lines : linesOfFile) {
            String[] data = lines.split(",");
            persistPositionDataToDB(data[0], data[1]);
        }
    }
}
