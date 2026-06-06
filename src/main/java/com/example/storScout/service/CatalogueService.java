package com.example.storScout.service;

import com.example.storScout.client.StoreClient;
import com.example.storScout.model.Product;
import com.example.storScout.util.CommandLineDisplayUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Scanner;

@Service
public class CatalogueService {

    private final String errorMessage = "An error occurred while getting the list of products: ";

    @Autowired
    StoreClient storeClient;

    @Autowired
    CommandLineDisplayUtility displayUtility;

    @Autowired
    Scanner sc;

    public void listProducts() {
        try {
            List<Product> allProducts = storeClient.getAllProducts();
            displayUtility.displayProductsTable(allProducts);
        } catch (Exception e) {
            System.out.println("An error occurred while getting the list of products: " + e.getMessage());
        }
    }

    public void viewProductById(int id) {
        try {
            Product product = storeClient.getProductById(id);
            if (product == null) {
                // Handling needs to be added explicitly here as the API responds with 200 OK status code even when product id is not available
                System.out.println("❌ Error: Product with ID " + id + " was not found 404");
            } else {
                displayUtility.displaySingleProductDetails(product);
            }
        } catch (HttpClientErrorException exception) {
            // Ideally, API should be giving 404 not found which will be caught here in case product with given id is not present
            System.out.println("❌ Error: Product with ID " + id + " was not found " + exception.getStatusCode());
        } catch (Exception e) {
            System.out.println(errorMessage + e.getMessage());
        }
    }

    public void viewProductsByCategory(String categoryName) {
        try {
            List<Product> allProducts = storeClient.getAllProducts();
            List<Product> filteredProducts = allProducts.stream().filter(product -> product.getCategory().equals(categoryName)).toList();
            displayUtility.displayProductsTable(filteredProducts);
        } catch (Exception e) {
            System.out.println(errorMessage + e.getMessage());
        }
    }

    public void addProduct() {
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Price: ");
        float price;
        try {
            price = Float.parseFloat(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid value for price");
            return;
        }
        System.out.print("Enter Category: ");
        String category = sc.nextLine();
        System.out.print("Enter Description: ");
        String description = sc.nextLine();
        System.out.println("Sending data to server...");
        Product toCreate = Product.builder().title(title).price(price).category(category).description(description).build();
        toCreate.setTitle(title);
        toCreate.setCategory(category);
        toCreate.setPrice(price);
        toCreate.setDescription(description);
        try {
            Product createdProduct = storeClient.addProduct(toCreate);
            System.out.println("✅️ Success! API returned new ID: " + createdProduct.getId());
            System.out.println("(Note: Mock server used; item will not appear in 'list')");
        } catch (Exception e) {
            System.out.println(errorMessage + e.getMessage());
        }
    }
}
