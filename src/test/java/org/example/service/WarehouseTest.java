package org.example.service;

import org.example.entities.Category;
import org.example.entities.ProductRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.example.entities.Product;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.example.entities.Category;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class WarehouseTest {
    private Warehouse warehouse;
    private Product mockProduct1;
    private Product mockProduct2;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Warehouse emptyWarehouse;
    @BeforeEach
    void setUp() {
        // Mock the products
        mockProduct1 = new Product("1", "Product 1", Category.ELECTRONICS, 5, LocalDate.of(2024, 3, 3), LocalDate.of(2024, 3, 4));
        mockProduct2 = new Product("2", "Product 2", Category.FURNITURE, 4, LocalDate.of(2024, 4, 3), LocalDate.of(2024, 4, 3));

        // Create the mocked warehouse
        warehouse = Mockito.spy(new Warehouse());

        // Inject the products into the warehouse's productList
        warehouse.addProduct(mockProduct1);
        warehouse.addProduct(mockProduct2);

        emptyWarehouse = Mockito.spy(new Warehouse());

        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void getProductByIdShouldPrintIfWrongProductId() {
        warehouse.getProductsById("1337");

        String message = outputStream.toString().trim();

        assertThat(message).contains("No product found of ID: 1337");
    }
    @Test
    void getProductByIdShouldReturnProduct() {
        assertAll("Attributes",
                () -> {
                    List<ProductRecord> filteredList = warehouse.getProductsById("1");
                    assertThat(filteredList).isNotEmpty();
                    assertAll(
                            () -> assertEquals("1", filteredList.getFirst().id()),
                            () -> assertEquals("Product 1", filteredList.getFirst().name())
                    );
                });

    }

    @Test
    void getProductListShouldReturnOutputMessageIfEmptyList() {
        emptyWarehouse.getProductList();
        String message = outputStream.toString().trim();
        assertThat(message).contains("Warehouse is empty");

    }

    @Test
    void getProductListShouldReturnAProductList(){
        assertThat(warehouse.getProductList().size()).isGreaterThan(0);
    }

    @Test
    void getProductByIdShouldPrintIfWrongProductCategory() {
        warehouse.getProductsByCategory(Category.HEALTH);

        String message = outputStream.toString().trim();

        assertThat(message).contains("No product found of category: HEALTH");
    }

    @Test
    @DisplayName("getProductByCategory should return not empty list if product exists")
    void getProductsByCategoryShouldReturnNotEmptyListIfProductExists(){
        assertAll("Attributes",
                () -> {
                    List<ProductRecord> filteredList = warehouse.getProductsByCategory(Category.ELECTRONICS);
                    assertThat(filteredList).isNotEmpty();
                    assertAll(
                            () -> assertEquals("1", filteredList.getFirst().id()),
                            () -> assertEquals("Product 1", filteredList.getFirst().name())
                    );
                });

    }



}