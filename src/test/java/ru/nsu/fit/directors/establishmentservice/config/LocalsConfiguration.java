package ru.nsu.fit.directors.establishmentservice.config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nsu.fit.directors.establishmentservice.configuration.UUIDGenerator;

@Configuration
public class LocalsConfiguration {
    @Bean
    public TestableClock clock() {
        return new TestableClock();
    }

    @Bean
    public UUIDGenerator uuidGenerator() {
        return UUID::randomUUID;
    }
}
