package de.telran.shoponline.repositiry;

import de.telran.shoponline.entity.Cart;
import de.telran.shoponline.entity.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles(profiles = {"dev"})
class CartRepositoryTest {
    private static final long CART_TEST_ID = 1;
    private static final long USER_TEST_ID = 1;
    private static final Users testUser = new Users();
    private static Cart testNewCart;
    @Autowired
    private CartRepository cartRepository;

    @BeforeAll
    static void setUp(){
        testUser.setName("Test");
        testNewCart = new Cart();
        testNewCart.setCartItems(new ArrayList<>());
        testNewCart.setUser(new Users());
    }
    @Test
    void testGet(){
        Optional<Cart> cart = cartRepository.findById(CART_TEST_ID);
        Assertions.assertTrue(cart.isPresent());
        Assertions.assertEquals(CART_TEST_ID,cart.get().getCartID());
        Assertions.assertEquals(USER_TEST_ID,cart.get().getUser().getUserID());
    }

    @Test
    void testInsert(){
        Cart returnCart = cartRepository.save(testNewCart);
        Assertions.assertNotNull(returnCart);
        Assertions.assertTrue(returnCart.getCartID()>0);

        Optional<Cart> findCart = cartRepository.findById(returnCart.getCartID());
        Assertions.assertTrue(findCart.isPresent());
        Assertions.assertEquals(testNewCart.getCartID(),findCart.get().getCartID());
    }

    @Test
    void testEdit(){
        Optional<Cart> cart = cartRepository.findById(CART_TEST_ID);
        Assertions.assertTrue(cart.isPresent());

        Cart getCart = cart.get();
        Assertions.assertEquals(CART_TEST_ID,getCart.getCartID());

        getCart.setUser(testUser);
        Cart returnCart = cartRepository.save(getCart);
        Assertions.assertNotNull(returnCart);
        Assertions.assertEquals(CART_TEST_ID,returnCart.getCartID());

        Optional<Cart> findCart = cartRepository.findById(CART_TEST_ID);
        Assertions.assertTrue(findCart.isPresent());
        Assertions.assertEquals("Test",findCart.get().getUser().getName());
    }

    @Test
    void deleteCart(){
        Cart returnCart = cartRepository.save(testNewCart);
        Assertions.assertNotNull(returnCart);
        Assertions.assertTrue(returnCart.getCartID()>0);

        Optional<Cart> findCart = cartRepository.findById(returnCart.getCartID());
        Assertions.assertTrue(findCart.isPresent());

        cartRepository.delete(findCart.get());

        Optional<Cart> findCartAfterDelete = cartRepository.findById(returnCart.getCartID());
        Assertions.assertFalse(findCartAfterDelete.isPresent());
    }
}