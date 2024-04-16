package de.telran.shoponline.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles(profiles = { "dev" })
class UsersServiceIntegrationTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUsers() {
        assertTrue(true);
    }
}