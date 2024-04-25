package de.telran.shoponline.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.entity.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles(profiles = { "dev" })
class UsersIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUsersTest() throws Exception {
         this.mockMvc.perform(get("/users")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..userID").exists());
    }

    @Test
    void getUserByIdTest() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/users/{id}", 1)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userID").value(1));
    }

    @Test
    void updateClientTest() throws Exception {
        UsersDto expectedUser = UsersDto.builder()
                .userID(1L)
                .email("test@test.com")
                .role(Role.CLIENT)
                .name("Test")
                .phoneNumber("123456789")
                .passwordHash("******")
                .build();
        String requestBody = objectMapper.writeValueAsString(expectedUser);

        this.mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userID").exists());
                .andExpect(jsonPath("$.userID").value(1));


    }
}