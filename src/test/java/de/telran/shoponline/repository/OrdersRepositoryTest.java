package de.telran.shoponline.repository;

import de.telran.shoponline.entity.Orders;
import de.telran.shoponline.entity.enums.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.sql.Timestamp;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = { "dev" })
class OrdersRepositoryTest {

    private static final int TEST_ID = 1;
    public static final String TEST_NEW_DELIVERY_ADDRESS = "New Delivery Address";
    private static Orders testOrder;

    @Autowired
    private OrdersRepository ordersRepositoryTest;

    @BeforeAll
    static void setUp() {
        testOrder = new Orders();
        testOrder.setCreatedAt(Timestamp.valueOf("2024-03-31 00:00:00.123456789"));
        testOrder.setDeliveryAddress("TestAddress");
        testOrder.setContactPhone("Test E-Mail");
        testOrder.setDeliveryMethod("Test Delivery Method");
        testOrder.setStatus(Status.OPEN);
        testOrder.setUpdateAt(Timestamp.valueOf("2024-03-31 00:00:00.123456789"));
    }

    @Test
    void testGet() {
        Optional<Orders> order = ordersRepositoryTest.findById(TEST_ID);
        assertTrue(order.isPresent());
        assertEquals(TEST_ID, order.get().getOrderId());
    }

    @Test
    void testInsert() {
        Orders returnOrder = ordersRepositoryTest.save(testOrder);
        assertNotNull(returnOrder);
        assertTrue(returnOrder.getOrderId()>0);

        Optional<Orders> findOrder = ordersRepositoryTest.findById(returnOrder.getOrderId());
        assertTrue(findOrder.isPresent());
        assertEquals(testOrder.getDeliveryAddress(), findOrder.get().getDeliveryAddress());
    }

    @Test
    void testEdit() {
        Optional<Orders> order = ordersRepositoryTest.findById(TEST_ID);
        assertTrue(order.isPresent());

        Orders getOrder = order.get();
        assertEquals(TEST_ID, getOrder.getOrderId());

        getOrder.setDeliveryAddress(TEST_NEW_DELIVERY_ADDRESS);
        Orders returnOrder = ordersRepositoryTest.save(getOrder);
        assertNotNull(returnOrder);
        assertEquals(TEST_ID,returnOrder.getOrderId());

        Optional<Orders> findOrder = ordersRepositoryTest.findById(TEST_ID);
        assertTrue(findOrder.isPresent());
        assertEquals(TEST_NEW_DELIVERY_ADDRESS, findOrder.get().getDeliveryAddress());
    }

    @Test
    void testDelete() {
        Orders returnOrder = ordersRepositoryTest.save(testOrder);
        assertNotNull(returnOrder);
        assertTrue(returnOrder.getOrderId()>0);

        Optional<Orders> findOrder = ordersRepositoryTest.findById(returnOrder.getOrderId());
        assertTrue(findOrder.isPresent());
        assertEquals(testOrder.getDeliveryAddress(), findOrder.get().getDeliveryAddress());

        ordersRepositoryTest.delete(findOrder.get());

        Optional<Orders> findUserAfterDelete = ordersRepositoryTest.findById(returnOrder.getOrderId());
        assertFalse(findUserAfterDelete.isPresent());
    }
}