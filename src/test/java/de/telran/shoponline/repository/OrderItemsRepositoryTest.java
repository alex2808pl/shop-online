package de.telran.shoponline.repository;

import de.telran.shoponline.entity.OrderItems;
import de.telran.shoponline.entity.Orders;
import de.telran.shoponline.entity.Products;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = { "dev" })
class OrderItemsRepositoryTest {

    private static OrderItems testOrderItems;
    private static final int TEST_ID = 1;
    public static final int TEST_NEW_QUANTITY = 50;

    @Autowired
    private OrderItemsRepository orderItemsRepositoryTest;

    @BeforeAll
    static void setUp() {
        testOrderItems = new OrderItems();
        testOrderItems.setOrder(new Orders());
        testOrderItems.setProduct(new Products());
        testOrderItems.setQuantity(100);
        testOrderItems.setPriceAtPurchase(90.00);
    }

    @Test
    void testGet() {
        Optional<OrderItems> orderItems = orderItemsRepositoryTest.findById(TEST_ID);
        assertTrue(orderItems.isPresent());
        assertEquals(TEST_ID, orderItems.get().getOrderItemID());
    }

    @Test
    void testInsert() {
        OrderItems returnOrderItems = orderItemsRepositoryTest.save(testOrderItems);
        assertNotNull(returnOrderItems);
        assertTrue(returnOrderItems.getOrderItemID()>0);

        Optional<OrderItems> findOrderItems = orderItemsRepositoryTest.findById(returnOrderItems.getOrderItemID());
        assertTrue(findOrderItems.isPresent());
        assertEquals(testOrderItems.getQuantity(), findOrderItems.get().getQuantity());
    }

    @Test
    void testEdit() {
        Optional<OrderItems> orderItems = orderItemsRepositoryTest.findById(TEST_ID);
        assertTrue(orderItems.isPresent());

        OrderItems getOrderItems = orderItems.get();
        assertEquals(TEST_ID, getOrderItems.getOrderItemID());

        getOrderItems.setQuantity(TEST_NEW_QUANTITY);
        OrderItems returnOrderItems = orderItemsRepositoryTest.save(getOrderItems);
        assertNotNull(returnOrderItems);
        assertEquals(TEST_ID,returnOrderItems.getOrderItemID());

        Optional<OrderItems> findOrderItems = orderItemsRepositoryTest.findById(TEST_ID);
        assertTrue(findOrderItems.isPresent());
        assertEquals(TEST_NEW_QUANTITY, findOrderItems.get().getQuantity());
    }

    @Test
    void testDelete() {
        OrderItems returnOrderItems = orderItemsRepositoryTest.save(testOrderItems);
        assertNotNull(returnOrderItems);
        assertTrue(returnOrderItems.getOrderItemID()>0);

        Optional<OrderItems> findOrderItems = orderItemsRepositoryTest.findById(returnOrderItems.getOrderItemID());
        assertTrue(findOrderItems.isPresent());
        assertEquals(testOrderItems.getQuantity(), findOrderItems.get().getQuantity());

        orderItemsRepositoryTest.delete(findOrderItems.get());

        Optional<OrderItems> findOrderItemsAfterDelete = orderItemsRepositoryTest.findById(returnOrderItems.getOrderItemID());
        assertFalse(findOrderItemsAfterDelete.isPresent());
    }
}