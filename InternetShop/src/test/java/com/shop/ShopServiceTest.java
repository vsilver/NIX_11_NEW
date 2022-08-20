package com.shop;

import com.shop.model.Customer;
import com.shop.model.Telephone;
import com.shop.model.invoice.Invoice;
import com.shop.model.product.Product;
import com.shop.model.product.ProductType;
import com.shop.model.television.Television;
import com.shop.service.ShopService;
import com.shop.utils.ParserCSV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

public class ShopServiceTest {
    private ShopService target;
    private BufferedReader reader;

    @BeforeEach
    void setUp() {
        target = new ShopService(new Random());
        reader = mock(BufferedReader.class);
    }

    @Test
    void createProducts() throws IOException {
        when(reader.readLine()).thenReturn(null);
        try (MockedStatic<ParserCSV> mockedStatic = mockStatic(ParserCSV.class)) {
            Telephone telephone = new Telephone(ProductType.TELEPHONE);
            mockedStatic.when(() -> ParserCSV.parseCSVToProducts(anyList())).thenReturn(List.of(telephone));
            List<Product> products = target.createProducts(reader);

            assertEquals(telephone, products.get(0));
            verify(reader, times(1)).readLine();
        }
    }

    @Test
    void generateRandomInvoice() throws IllegalAccessException {
        List<Product> products = List.of(new Telephone(ProductType.TELEPHONE), new Television(ProductType.TELEVISION), new Telephone(ProductType.TELEPHONE));
        for (Field declaredField : target.getClass().getDeclaredFields()) {
            if (declaredField.getName().endsWith("products")) {
                declaredField.setAccessible(true);
                declaredField.set(target, products);
            }
        }
        Customer customer = new Customer();
        Invoice invoice = target.generateRandomInvoice((i -> i > 100), customer);

        assertFalse(invoice.getProductList().isEmpty());
        assertSame(customer, invoice.getCustomer());
    }

    @Test
    void getInvoiceSum() {
        Telephone telephone1 = new Telephone(ProductType.TELEPHONE);
        telephone1.setPrice(100);
        Telephone telephone2 = new Telephone(ProductType.TELEPHONE);
        telephone2.setPrice(100);

        long actual = target.getInvoiceSum(List.of(telephone1, telephone2));
        long expected = 200;

        assertEquals(expected, actual);
    }
}
