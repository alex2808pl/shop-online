package de.telran.shoponline.repositiry;

import de.telran.shoponline.entity.Users;
import de.telran.shoponline.entity.enums.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = { "dev" })
class UsersRepositoryTest {
    public static final long TEST_ID = 1L;
    public static final String TEST_NEW_NAME = "NewTestName";
    private static Users testNewUser;

    @Autowired
    private UsersRepository usersRepositoryTest;

    @BeforeAll
    static void setUp() {
        testNewUser = new Users();
        testNewUser.setName("TestName");
        testNewUser.setEmail("Test E-Mail");
        testNewUser.setRole(Role.CLIENT);
        testNewUser.setPasswordHash("Test Password Hash");
        testNewUser.setPhoneNumber("Test Phone Nummer");
    }

    @Test
    void testGet() {
        Optional<Users> user = usersRepositoryTest.findById(TEST_ID);
        assertTrue(user.isPresent());
        assertEquals(TEST_ID, user.get().getUserID());
    }
    @Test
    void testInsert() {

        // Найти тестовый элемент корзины cartTest
        // testNewUser.setCart(cartTest);

        Users returnUser = usersRepositoryTest.save(testNewUser);
        assertNotNull(returnUser);
        assertTrue(returnUser.getUserID()>0);

        Optional<Users> findUser = usersRepositoryTest.findById(returnUser.getUserID());
        assertTrue(findUser.isPresent());
        assertEquals(testNewUser.getName(), findUser.get().getName());
    }

    @Test
    void testEdit() {
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

        Users returnUser = usersRepositoryTest.save(testNewUser);
        assertNotNull(returnUser);
        assertTrue(returnUser.getUserID()>0);

        Optional<Users> findUser = usersRepositoryTest.findById(returnUser.getUserID());
        assertTrue(findUser.isPresent());
        assertEquals(testNewUser.getName(), findUser.get().getName());

        usersRepositoryTest.delete(findUser.get());

        Optional<Users> findUserAfterDelete = usersRepositoryTest.findById(returnUser.getUserID());
        assertFalse(findUserAfterDelete.isPresent());

    }
}