package de.telran.shoponline.repositiry;

import de.telran.shoponline.entity.Categories;
import de.telran.shoponline.entity.Products;
import de.telran.shoponline.entity.Users;
import de.telran.shoponline.entity.enums.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = { "dev" })
public class ProductRepositoryTest {

    public static final long TEST_ID = 1L;
    public static final String TEST_NEW_NAME = "NewTestName";
    private static Users testNewUser;

    @Autowired
    private ProductsRepository productsRepositoryTest;

    @BeforeAll
    static void setUp() {
        Products testNewProduct = new Products();
        testNewProduct.setName("TestName");
        testNewProduct.setDescription("Test description");
        testNewProduct.setPrice(20);
        testNewProduct.setImageURL("url");
    }

    @Test
    void testGet() {
        Optional<Products> product = productsRepositoryTest.findById(TEST_ID);
        assertTrue(product.isPresent());
        assertEquals(TEST_ID, product.get().getProductID());
    }

    @Test
    void testInsert() {

        Products returnProduct = productsRepositoryTest.save(testNewProduct);
        assertNotNull(returnProduct);
        assertTrue(returnProduct.getProductID()>0);

        Optional<Products> findProduct = productsRepositoryTest.findById(returnProduct.getProductID());
        assertTrue(findProduct.isPresent());
        assertEquals(testNewProduct.getName(), findProduct.get().getName());
    }

    @Test
    void testEdit() {
        Optional<Products> product = productsRepositoryTest.findById(TEST_ID);
        assertTrue(product.isPresent());

        Products getProduct = product.get();
        assertEquals(TEST_ID, getProduct.getProductID());

        getProduct.setName(TEST_NEW_NAME);
        Products returnProduct = productsRepositoryTest.save(getProduct);
        assertNotNull(returnProduct);
        assertEquals(TEST_ID,returnProduct.getProductID());

        Optional<Products> findProduct = productsRepositoryTest.findById(TEST_ID);
        assertTrue(findProduct.isPresent());
        assertEquals(TEST_NEW_NAME, findProduct.get().getName());
    }

    @Test
    void testDelete() {
        Products returnProduct = productsRepositoryTest.save(testNewProduct);
        assertNotNull(returnProduct);
        assertTrue(returnProduct.getProductID()>0);

        Optional<Products> findProduct = productsRepositoryTest.findById(returnProduct.getProductID());
        assertTrue(findProduct.isPresent());
        assertEquals(testNewProduct.getName(), findProduct.get().getName());

        productsRepositoryTest.delete(findProduct.get());

        Optional<Products> findProductAfterDelete = productsRepositoryTest.findById(returnProduct.getProductID());
        assertFalse(findProductAfterDelete.isPresent());

    }
}
