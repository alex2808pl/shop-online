package de.telran.shoponline.repositiry;

import de.telran.shoponline.entity.Users;
import de.telran.shoponline.entity.enums.Role;
import org.h2.engine.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = { "dev" })
class UsersRepositoryTest {
    @Autowired
    private UsersRepository usersRepositoryTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGet() {
        Optional<Users> user = usersRepositoryTest.findById(1L);
        assertTrue(user.isPresent());
        assertEquals(1L, user.get().getUserID());
    }
    @Test
    void testInsert() {
        Users testUsers = new Users();
        testUsers.setName("TestName");
        testUsers.setEmail("Test E-Mail");
        testUsers.setRole(Role.CLIENT);
        testUsers.setPasswordHash("Test Password Hash");
        testUsers.setPhoneNumber("Test Phone Nummer");

        Users returnUser = usersRepositoryTest.save(testUsers);
        assertNotNull(returnUser);
        assertTrue(returnUser.getUserID()>0);

        Optional<Users> findUser = usersRepositoryTest.findById(returnUser.getUserID());
        assertTrue(findUser.isPresent());
        assertEquals(testUsers.getName(), findUser.get().getName());
    }

    @Test
    void testEdit() {
        final long TEST_ID = 1;
        final String TEST_NEW_NAME = "NewTestName";

        Optional<Users> user = usersRepositoryTest.findById(TEST_ID);
        assertTrue(user.isPresent());

        Users getUser = user.get();
        assertEquals(TEST_ID, getUser.getUserID());

        getUser.setName(TEST_NEW_NAME);
        Users returnUser = usersRepositoryTest.save(getUser);
        assertNotNull(returnUser);
        assertEquals(TEST_ID,returnUser.getUserID());

        Optional<Users> findUser = usersRepositoryTest.findById(TEST_ID);
        assertTrue(findUser.isPresent());
        assertEquals(TEST_NEW_NAME, findUser.get().getName());

    }

    @Test
    void testDelete() {
        Users testUsers = new Users();
        testUsers.setName("TestName");
        testUsers.setEmail("Test E-Mail");
        testUsers.setRole(Role.CLIENT);
        testUsers.setPasswordHash("Test Password Hash");
        testUsers.setPhoneNumber("Test Phone Nummer");

        Users returnUser = usersRepositoryTest.save(testUsers);
        assertNotNull(returnUser);
        assertTrue(returnUser.getUserID()>0);

        Optional<Users> findUser = usersRepositoryTest.findById(returnUser.getUserID());
        assertTrue(findUser.isPresent());
        assertEquals(testUsers.getName(), findUser.get().getName());

        usersRepositoryTest.delete(findUser.get());

        Optional<Users> findUserAfterDelete = usersRepositoryTest.findById(returnUser.getUserID());
        assertFalse(findUserAfterDelete.isPresent());

    }
}