package com.example.storScout.controller;

import com.example.storScout.service.CatalogueService;
import com.example.storScout.util.CommandLineDisplayUtility;
import com.github.lalyos.jfiglet.FigletFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class StoreScoutCLI implements CommandLineRunner {
    @Autowired
    private CatalogueService catalogueService;

    @Autowired
    private Scanner sc;

    @Autowired
    private CommandLineDisplayUtility displayUtility;

    private boolean killSwitch;

    StoreScoutCLI() {
        killSwitch = true;
    }

    private void listProductsAtStartup() {
        System.out.println("> list");
        catalogueService.listProducts();
    }

    private void startInputLoop() {
        while (killSwitch) {
            processInput();
        }
    }

    private void processInput() {
        System.out.print("> ");
        String input = sc.nextLine();
        String[] inpParams = input.split(" ");
        if (inpParams.length == 0) {
            return;
        }
        String command = inpParams[0];
        switch (command) {
            case "list" -> catalogueService.listProducts();
            case "view" -> {
                if (inpParams.length < 2) {
                    System.out.println("⚠\uFE0F No id provided to view product");
                    return;
                }
                int id;
                try {
                    id = Integer.parseInt(inpParams[1]);
                } catch (NumberFormatException e) {
                    System.out.println("⚠\uFE0F Please provide a valid product id");
                    return;
                }
                catalogueService.viewProductById(id);
            }
            case "category" -> {
                if (inpParams.length < 2) {
                    System.out.println("⚠\uFE0F No category name provided to view products");
                    return;
                }
                String categoryName = "";
                for (int i = 1; i < inpParams.length; i++) {
                    categoryName += inpParams[i] + " ";
                }
                categoryName = categoryName.trim();
                catalogueService.viewProductsByCategory(categoryName);
            }
            case "add" -> catalogueService.addProduct();
            case "help" -> displayUtility.showHelpMenu();
            case "exit" -> exitApp();
            default -> System.out.println("⚠\uFE0F Unknown command: " + command);
        }
    }

    private void exitApp() {
        killSwitch = false;
    }

    @Override
    public void run(String... args) throws Exception {
        displayUtility.showWelcomeMessage();
        listProductsAtStartup();
        startInputLoop();
        displayUtility.showExitMessage();
        sc.close();
    }
}
