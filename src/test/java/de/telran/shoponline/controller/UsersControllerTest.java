package de.telran.shoponline.controller;

import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsersService usersServiceMock;

    @Test
    void getUsersTest() throws Exception {
        when(usersServiceMock.getUsers()).thenReturn(List.of(UsersDto.builder().userID(1L).build()));
        this.mockMvc.perform(get("/users")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..userID").exists());
    }

    @Test
    void getUserByIdTest() throws Exception {
        when(usersServiceMock.getUserById(anyLong())).thenReturn(UsersDto.builder().userID(1L).build());
        this.mockMvc.perform(get("/users/{id}", 1)).andDo(print())
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userID").exists());
                .andExpect(jsonPath("$.userID").value(1));
    }
}