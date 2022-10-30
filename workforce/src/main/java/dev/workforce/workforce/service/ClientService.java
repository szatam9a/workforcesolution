package dev.workforce.workforce.service;

import dev.workforce.workforce.dto.CreateClientCommand;
import dev.workforce.workforce.entity.Client;
import dev.workforce.workforce.exception.EmailAddressAlreadyTakenException;
import dev.workforce.workforce.exception.InvalidApiKeyException;
import dev.workforce.workforce.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;

    public UUID createNewClient(CreateClientCommand createClientCommand) {

        if (clientRepository.findByEmailAddress(createClientCommand.getEmailAddress()).isPresent()) {
            throw new EmailAddressAlreadyTakenException(createClientCommand.getEmailAddress());
        }
        Client client = new Client();
        client.setApikey(UUID.randomUUID());
        client.setName(createClientCommand.getName());
        client.setEmailAddress(createClientCommand.getEmailAddress());
        clientRepository.save(client);
        return client.getApikey();
    }

    public void validateApiKey(String apiKey) {
        if (clientRepository.findById(UUID.fromString(apiKey)).isEmpty()) {
            throw new InvalidApiKeyException(apiKey);
        }
    }

    @PostConstruct
    public  void initClientData() {
        Path path = Path.of("workforce/src/main/resources/initdataclient.csv");
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
            persistClientDataToDB(data[0], data[1], data[2]);
        }
    }

    private void persistClientDataToDB(String id, String name, String emailAddress) {
        Client client = new Client();
        client.setApikey(UUID.fromString(id));
        client.setName(name);
        client.setEmailAddress(emailAddress);
        clientRepository.save(client);
    }


}
