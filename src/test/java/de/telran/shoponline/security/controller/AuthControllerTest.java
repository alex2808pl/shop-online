package de.telran.shoponline.security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.shoponline.security.jwt.JwtRequest;
import de.telran.shoponline.security.jwt.JwtRequestRefresh;
import de.telran.shoponline.security.jwt.JwtResponse;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles(profiles = { "dev" })
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static String jwtRefreshTest;

    @Test
    @Order(1)
    void loginTest() throws Exception {
        JwtRequest authRequestTest = new JwtRequest();
        authRequestTest.setLogin("admin@example.com");
        authRequestTest.setPassword("1234");
        String requestBody = objectMapper.writeValueAsString(authRequestTest);

        String content = this.mockMvc.perform(post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.accessToken").exists())
                        .andExpect(jsonPath("$.refreshToken").exists())
                        .andExpect(jsonPath("$.type").value("Bearer"))
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        JwtResponse responseTest = objectMapper.readValue(content, JwtResponse.class);
        jwtRefreshTest = responseTest.getRefreshToken();
    }

    @Test
    @Order(2)
    void getNewAccessTokenTest() throws Exception {

        //тестируем
        JwtRequestRefresh authRefreshTest = new JwtRequestRefresh();
        authRefreshTest.setRefreshToken(jwtRefreshTest);
        String requestBody = objectMapper.writeValueAsString(authRefreshTest);

        this.mockMvc.perform(post("/auth/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").doesNotExist())
                .andExpect(jsonPath("$.type").value("Bearer"));
    }

    @Test
    void getNewAccessTokenAllTest() throws Exception {
        //готовим информацию
        JwtRequest authRequestTest = new JwtRequest();
        authRequestTest.setLogin("admin@example.com");
        authRequestTest.setPassword("1234");
        String requestBody = objectMapper.writeValueAsString(authRequestTest);

        String content = this.mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").exists())
                .andExpect(jsonPath("$.type").value("Bearer"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JwtResponse responseTest = objectMapper.readValue(content, JwtResponse.class);

        //тестируем
        JwtRequestRefresh authRefreshTest = new JwtRequestRefresh();
        authRefreshTest.setRefreshToken(responseTest.getRefreshToken());
        requestBody = objectMapper.writeValueAsString(authRefreshTest);

        this.mockMvc.perform(post("/auth/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").doesNotExist())
                .andExpect(jsonPath("$.type").value("Bearer"));
    }
}