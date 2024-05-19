package ru.nsu.fit.directors.establishmentservice;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.flyway.OptimizedFlywayTestExecutionListener;
import jakarta.annotation.Nonnull;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.fit.directors.establishmentservice.config.TestableClock;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@FlywayTest
@TestExecutionListeners({
    OptimizedFlywayTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class,
    ClearDatabaseExtension.class
})
@AutoConfigureEmbeddedDatabase
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "integration-test")
class EstablishmentServiceApplicationTests {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected TestableClock clock;

    @BeforeEach
    public void setup() {
        clock.setFixed(Instant.parse("2024-05-19T10:15:30.00Z"));
    }

    @Nonnull
    protected static MockHttpServletRequestBuilder postMultipartData(String path, String photoPath, String photoName)
        throws IOException {
        Resource imageResources = new ClassPathResource(photoPath);
        var image = new MockMultipartFile(photoName,
            "",
            MediaType.MULTIPART_FORM_DATA_VALUE,
            imageResources.getContentAsByteArray()
        );
        return MockMvcRequestBuilders.multipart(HttpMethod.POST, path).file(image);
    }

    @Nonnull
    protected static ResultMatcher responseFromPath(String path) throws IOException {
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        JsonNode node = objectMapper.readTree(input);
        String text = objectMapper.writeValueAsString(node);
        return content().json(text);
    }

}
