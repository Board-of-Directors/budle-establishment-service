package ru.nsu.fit.directors.establishmentservice.configuration;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalsConfiguration {
    @Bean
    public UUIDGenerator uuidGenerator() {
        return UUID::randomUUID;
    }
}
