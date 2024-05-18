package ru.nsu.fit.directors.establishmentservice;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тест загрузки изображений")
class EstablishmentImageTest extends EstablishmentServiceApplicationTests {
    private static final UUID FIXED_UUID = UUID.fromString("ddf8635e-c1df-431a-81bc-93784afc7a69");

    @Test
    @DisplayName("Загрузка изображения")
    void uploadingImageTest() throws Exception {
        try (MockedStatic<UUID> mocked = mockStatic(UUID.class)) {
            mocked.when(UUID::randomUUID).thenReturn(FIXED_UUID);
            mockMvc.perform(postMultipartData(
                    "/establishment/image",
                    "http/request/image/water.png",
                    "image"
                ))
                .andExpect(status().is2xxSuccessful())
                .andExpect(responseFromPath("http/response/image/uploaded_response.json"));
        }
    }
}
