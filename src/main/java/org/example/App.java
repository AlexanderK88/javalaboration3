package org.example;

import org.example.entities.*;
import org.example.service.Warehouse;
import org.example.entities.PopulateWarehouse;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class App {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    Warehouse warehouse = new Warehouse();
    PopulateWarehouse.populateWarehouse(warehouse);

    boolean showMenu = true;

    while (showMenu) {
      showMenu();

      int choice = getUserChoice();

      switch (choice) {
        case 1 -> warehouse.getProductList().forEach(System.out::println);
        case 2 -> addProduct(warehouse);
        case 3 -> modifyProduct(warehouse);
        case 18 -> showProductsCountByCategory(warehouse);
        case 19 -> warehouse.getCategoriesWithProducts().forEach(System.out::println);
        case 20 -> showProductsGroupedByFirstLetter(warehouse);
        case 21 -> showMenu = false;
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private static void showMenu() {
    System.out.println("\nWarehouse Management Menu:");
    System.out.println("1. List all products");
    System.out.println("2. Add a new product");
    System.out.println("3. Modify a product");
    System.out.println("4. View products by category");
    System.out.println("5. View product by ID");
    System.out.println("6. View products by creation date");
    System.out.println("7. View products modified since creation");
    System.out.println("18. Show amount of products in a category");
    System.out.println("19. Get all categories with at least 1 product");
    System.out.println("20. Count how many product start with the same letter");
    System.out.println("21. Exit");
    System.out.print("Enter your choice: ");
  }

  private static int getUserChoice() {
    return Integer.parseInt(scanner.nextLine());
  }

  private static void addProduct(Warehouse warehouse) {
    int id;
    if (warehouse.getProductList().isEmpty()) {
      id = 1;
    } else {
      id = warehouse.getProductList().size() + 1;
    }

    Category category = null;

    System.out.print("Enter product name: ");
    String name = scanner.nextLine();

    while (category == null) {
      System.out.print("Enter product category (ELECTRONICS, FURNITURE, etc.): ");
      String input = scanner.nextLine().toUpperCase();

      try {
        category = Category.valueOf(input);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid category. Please enter a valid category");
      }
    }

    System.out.print("Enter product rating 1-10: ");
    int rating = Integer.parseInt(scanner.nextLine());

    warehouse.addProduct(new Product(String.valueOf(id), name, category, rating, LocalDate.now(), LocalDate.now()));
    System.out.println("Added product successfully.");
  }

  private static void modifyProduct(Warehouse warehouse) {
    System.out.print("Enter the ID of the product you want to modify: ");
    String id = scanner.nextLine();

    System.out.println("What would you like to modify?");
    System.out.println("1. Category");
    System.out.println("2. Rating");
    System.out.println("3. Name");
    System.out.print("Enter your choice: ");
    int modifyChoice = Integer.parseInt(scanner.nextLine());

    String typOfChange = null;
    String change = null;

    switch (modifyChoice) {
      case 1 -> {
        typOfChange = "category";
        Category category = null;
        while (category == null) {
          System.out.print("Enter the new category (ELECTRONICS, FURNITURE, etc.): ");
          String input = scanner.nextLine().toUpperCase();
          try {
            category = Category.valueOf(input);
            change = category.name();
          } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Please enter a valid category.");
          }
        }
      }
      case 2 -> {
        typOfChange = "rating";
        System.out.print("Enter the new rating (1-10): ");
        change = scanner.nextLine();
      }
      case 3 -> {
        typOfChange = "name";
        System.out.print("Enter the new name: ");
        change = scanner.nextLine();
      }
      default -> System.out.println("Invalid choice. No modification will be made.");
    }

    if (typOfChange != null && change != null) {
      warehouse.modifyProduct(id, typOfChange, change);
      System.out.println("Product modified successfully.");
    }
  }

  private static void showProductsCountByCategory(Warehouse warehouse) {
    Category category = null;

    while (category == null) {
      System.out.print("Enter the product category to count (ELECTRONICS, FURNITURE, etc.): ");
      String input = scanner.nextLine().toUpperCase();

      try {
        category = Category.valueOf(input);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid category. Please enter a valid category.");
      }
    }

    int count = warehouse.countProductsInCategory(category);
    System.out.println("Number of products in category " + category + ": " + count);
  }

  private static void showProductsGroupedByFirstLetter(Warehouse warehouse) {
    Map<Character, Long> productCounts = warehouse.numberOfProductsWithSameFirstLetter();
    productCounts.forEach((initial, count) ->
            System.out.printf("Initial: %c, Count: %d%n", initial, count)
    );
  }
}


