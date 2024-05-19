package ru.nsu.fit.directors.establishmentservice.configuration;

import java.time.Clock;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value = "!integration-test")
public class LocalsConfiguration {
    @Bean
    public UUIDGenerator uuidGenerator() {
        return UUID::randomUUID;
    }

    @Bean
    public Clock clock() {
        return Clock.system(ZoneOffset.systemDefault());
    }
}
