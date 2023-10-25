package ru.nsu.fit.directors.establishmentservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(servers = {
    @Server(
        description = "This is the localhost",
        url = "localhost:8080/establishment"
    ),
    @Server(
        description = "This is the real server",
        url = "80.87.200.185/establishment"
    )
})
public class EstablishmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstablishmentServiceApplication.class, args);
    }

}
