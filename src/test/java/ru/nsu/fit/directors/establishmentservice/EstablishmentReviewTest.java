package ru.nsu.fit.directors.establishmentservice;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Проверка работы с отзывами")
@DatabaseSetup("/database/establishment/default_establishment.xml")
class EstablishmentReviewTest extends EstablishmentServiceApplicationTests {

    @Test
    @DisplayName("Получение отзывов")
    @DatabaseSetup("/database/review/review_list.xml")
    void getReviewsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/establishment/review/all?establishmentId=100"))
            .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
            .andExpect(status().is2xxSuccessful())
            .andExpect(responseFromPath("http/response/review/review_list_response.json"));
    }
}
