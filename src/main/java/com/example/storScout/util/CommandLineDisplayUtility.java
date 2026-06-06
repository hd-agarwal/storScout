package com.example.storScout.util;

import com.example.storScout.model.Product;
import com.github.lalyos.jfiglet.FigletFont;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CommandLineDisplayUtility {
    private final int separatorLength = 75;
    private final String welcomeMessage = "Welcome to StorScout! Type 'help' for commands";
    private final String exitMessage = "Thank you for using our catalogue utility";


    private void displayWithPadding(String text, int padding) {
        System.out.print(text);
        for (int i = text.length(); i <= padding; i++) {
            System.out.print(" ");
        }
    }

    private void displaySeparatorLine() {
        System.out.println();
        for (int i = 0; i < separatorLength; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void showWelcomeMessage() {
        try {
            String bannerString = FigletFont.convertOneLine("Stor Scout");
            System.out.println(bannerString);
        } catch (IOException e) {
            System.out.println("An error occurred while displaying the banner " + e.getMessage());
        }
        System.out.println(welcomeMessage);
    }

    public void showExitMessage() {
        System.out.println(exitMessage);
    }

    public void displayProductsTable(List<Product> products) {
        System.out.println();
        displayWithPadding("[ID]", 7);
        displayWithPadding("Price", 10);
        displayWithPadding("Title", 0);
        displaySeparatorLine();
        for (Product product : products) {
            displayWithPadding("[" + product.getId() + "]", 7);
            displayWithPadding("$" + product.getPrice(), 10);
            displayWithPadding(product.getTitle(), 0);
            System.out.println();
        }
        System.out.println("(List Complete)");
        System.out.println();
    }

    public void displaySingleProductDetails(Product product) {
        System.out.println();
        System.out.print("\uD83D\uDCE6\uFE0F PRODUCT DETAILS");
        displaySeparatorLine();
        displayWithPadding("Name:", 14);
        System.out.println(product.getTitle());
        displayWithPadding("Category:", 14);
        System.out.println(product.getCategory());
        displayWithPadding("Price:", 14);
        System.out.println("$" + product.getPrice());
        displayWithPadding("Description:", 14);
        System.out.print(product.getDescription());
        displaySeparatorLine();
        System.out.println();
    }

    public void showHelpMenu() {
        System.out.println();
        displayWithPadding("Command", 20);
        displayWithPadding("Usage", 30);
        displayWithPadding("Description", 0);
        displaySeparatorLine();
        displayWithPadding("list", 20);
        displayWithPadding("list", 30);
        displayWithPadding("List all products", 0);
        System.out.println();
        displayWithPadding("view", 20);
        displayWithPadding("view 1", 30);
        displayWithPadding("Get details of product with id 1", 0);
        System.out.println();
        displayWithPadding("category", 20);
        displayWithPadding("category electronics", 30);
        displayWithPadding("List all products in the electronics category", 0);
        System.out.println();
        displayWithPadding("add", 20);
        displayWithPadding("add <prompts for details>", 30);
        displayWithPadding("Add a new product", 0);
        System.out.println();
        displayWithPadding("help", 20);
        displayWithPadding("help", 30);
        displayWithPadding("Show this menu", 0);
        System.out.println();
        displayWithPadding("exit", 20);
        displayWithPadding("exit", 30);
        displayWithPadding("Exit the application", 0);
        System.out.println();

    }
}
