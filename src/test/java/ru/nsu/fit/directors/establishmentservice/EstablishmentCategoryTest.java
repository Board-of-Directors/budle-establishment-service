package ru.nsu.fit.directors.establishmentservice;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Проверка данных заведений")
@DatabaseSetup("/database/establishment/default_establishment.xml")
class EstablishmentCategoryTest extends EstablishmentServiceApplicationTests {
    @Test
    @DisplayName("Получение списка категорий")
    void getCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/establishment/category"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(responseFromPath("http/response/category/all_categories.json"));
    }

    @Test
    @DisplayName("Получение списка тэгов")
    void getTags() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/establishment/tags"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(responseFromPath("http/response/tags/all_tags.json"));
    }

    @Test
    @DisplayName("Получение валидного времени бронирования заведения")
    void getEstablishmentValidTime() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/establishment/time?establishmentId=100"))
            .andExpect(responseFromPath("http/response/establishment/valid_time.json"));
    }


    /*@Test
    @DisplayName("Получение валидного времени бронирования заведения")
    void getEstablishmentValidTimeV2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/establishment/internal/time?establishmentId=100"))
            .andExpect(responseFromPath("http/response/establishment/valid_time_internal.json"));
    }

     */
}
