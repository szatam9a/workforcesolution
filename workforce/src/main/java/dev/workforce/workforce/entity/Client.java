package dev.workforce.workforce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @Column(name = "client_id")
    private UUID apikey;
    @Column(name = "client_name")
    private String name;
    @Column(name = "client_emailAddress")
    private String emailAddress;
}
