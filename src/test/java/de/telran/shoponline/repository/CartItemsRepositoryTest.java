package de.telran.shoponline.repository;

import de.telran.shoponline.entity.Cart;
import de.telran.shoponline.entity.CartItems;
import de.telran.shoponline.entity.Products;
import de.telran.shoponline.repository.CartItemsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles(profiles = {"dev"})
class CartItemsRepositoryTest {
    private static final long TEST_ID = 1;
    private static final String TEST_NAME = "test";
    private static CartItems testCartItems;
    @Autowired
    private CartItemsRepository cartItemsRepository;

    @BeforeAll
    static void setUp() {
        testCartItems = new CartItems();
        testCartItems.setCartItemID(TEST_ID);
        testCartItems.setCart(new Cart());
        testCartItems.setProduct(new Products());
    }

    @Test
    void testGet() {
        Optional<CartItems> cartItems = cartItemsRepository.findById(TEST_ID);
        Assertions.assertTrue(cartItems.isPresent());
        Assertions.assertEquals(TEST_ID, cartItems.get().getCartItemID());
    }

    @Test
    void testInsert() {
        CartItems returnCartItems = cartItemsRepository.save(testCartItems);
        Assertions.assertNotNull(returnCartItems);
        Assertions.assertTrue(returnCartItems.getCartItemID() > 0);

        Optional<CartItems> findCartItems = cartItemsRepository.findById(returnCartItems.getCartItemID());
        Assertions.assertTrue(findCartItems.isPresent());
        Assertions.assertEquals(testCartItems.getCartItemID(), findCartItems.get().getCartItemID());
    }

    @Test
    void testEdit() {
        Optional<CartItems> cartItems = cartItemsRepository.findById(TEST_ID);
        Assertions.assertTrue(cartItems.isPresent());

        CartItems getCartItems = cartItems.get();
        Assertions.assertEquals(TEST_ID, getCartItems.getCartItemID());

        getCartItems.getProduct().setName(TEST_NAME);
        CartItems returnCartItems = cartItemsRepository.save(getCartItems);
        Assertions.assertNotNull(returnCartItems);
        Assertions.assertEquals(TEST_ID, returnCartItems.getCartItemID());

        Optional<CartItems> findCartItem = cartItemsRepository.findById(TEST_ID);
        Assertions.assertTrue(findCartItem.isPresent());
        Assertions.assertEquals(TEST_NAME, findCartItem.get().getProduct().getName());
    }

    @Test
    void testDelete() {
        CartItems returnCartItems = cartItemsRepository.save(testCartItems);
        Assertions.assertNotNull(returnCartItems);
        Assertions.assertTrue(returnCartItems.getCartItemID() > 0);

        Optional<CartItems> findCartItems = cartItemsRepository.findById(returnCartItems.getCartItemID());
        Assertions.assertTrue(findCartItems.isPresent());
        Assertions.assertEquals(testCartItems.getCartItemID(),findCartItems.get().getCartItemID());

        cartItemsRepository.delete(findCartItems.get());

        Optional<CartItems> findCartItemsAfterDelete = cartItemsRepository.findById(returnCartItems.getCartItemID());
        Assertions.assertFalse(findCartItemsAfterDelete.isPresent());
    }
}