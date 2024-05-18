package ru.nsu.fit.directors.establishmentservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Проверка категорий заведений")
class EstablishmentCategoryTest extends EstablishmentServiceApplicationTests {
    @Test
    @DisplayName("Получение списка категорий")
    void getCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/establishment/category"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(responseFromPath("http/response/category/all_categories.json"));
    }
}
