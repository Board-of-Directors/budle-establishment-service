package ru.nsu.fit.directors.establishmentservice;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тест на создание и редактирование заведений")
@DatabaseSetup("/database/establishment/default_establishment.xml")
class EstablishmentTest extends EstablishmentServiceApplicationTests {

    @Test
    @DisplayName("Тест на редактирование заведения")
    @DatabaseSetup("/database/establishment/detached_image.xml")
    @ExpectedDatabase(
        value = "/database/establishment/changed_establishment.xml",
        assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    void testChangeEstablishment() throws Exception {
        mockMvc.perform(putJsonBody(
                "/internal/establishment?establishmentId=100",
                "http/request/establishment/change_establishment_body.json"
            ))
            .andExpect(status().is2xxSuccessful());
    }
}
