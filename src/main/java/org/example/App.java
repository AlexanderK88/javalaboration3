package org.example;

import org.example.entities.*;
import org.example.service.Warehouse;
import org.example.entities.PopulateWarehouse;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
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
        case 4 -> viewProductsByCategory(warehouse);
        case 5 -> viewProductById(warehouse);
        case 6 -> viewProductsByCreationDate(warehouse);
        case 7 -> viewModifiedProducts(warehouse);
        case 8 -> viewProductsCountByCategory(warehouse);
        case 9 -> warehouse.getCategoriesWithProducts().forEach(System.out::println);
        case 10 -> viewProductsGroupedByFirstLetter(warehouse);
        case 11 -> warehouse.getProductsWithMaxRatingThisMonth(LocalDate.now()).forEach(System.out::println);
        case 12 -> showMenu = false;
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
    System.out.println("8. Show amount of products in a category");
    System.out.println("9. Get all categories with at least 1 product");
    System.out.println("10. Count how many product start with the same letter");
    System.out.println("11. Get products with max rating this month");
    System.out.println("12. Exit");
    System.out.print("Enter your choice: ");
  }

  private static int getUserChoice() {
    return checkValidIntInput(1, 12);
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
      int rating = checkValidIntInput(1, 10);

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
        change = String.valueOf(checkValidIntInput(1, 10));
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

  private static int checkValidIntInput(int min, int max) {
    Scanner scanner = new Scanner(System.in);
    int change;
    while(true) {
      try {
        change = scanner.nextInt();
        if (change >= min && change <= max) {
          return change;
      }else{
          throw new InputMismatchException();
        }
      }catch (InputMismatchException e){
        System.out.print("Please add a rating between" + min + " - " + max + ": ");
        scanner.nextLine();
      }
    }
  }

  private static void viewProductsByCategory(Warehouse warehouse) {
    Category category = null;

    while (category == null) {
      System.out.print("Enter the category: ");
      String categoryInput = scanner.nextLine().toUpperCase();
      try {
        category = Category.valueOf(categoryInput);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid category. Please enter a valid category.");
      }
    }

    List<ProductRecord> products = warehouse.getProductsByCategory(category);
    products.forEach(System.out::println);
  }

  private static void viewProductById(Warehouse warehouse) {
    System.out.print("Enter the product ID: ");
    String id = scanner.nextLine();
    List<ProductRecord> products = warehouse.getProductsById(id);
    products.forEach(System.out::println);
  }

  private static void viewProductsByCreationDate(Warehouse warehouse) {
    LocalDate date = null;

    while (date == null) {
      System.out.print("Enter the date (YYYY-MM-DD): ");
      String dateInput = scanner.nextLine();
      try {
        date = LocalDate.parse(dateInput);
      } catch (Exception e) {
        System.out.println("Invalid date format. Please enter a valid date.");
      }
    }

    List<ProductRecord> products = warehouse.getProductsByCreationDate(date);
    products.forEach(System.out::println);
  }

  private static void viewModifiedProducts(Warehouse warehouse) {
    List<ProductRecord> products = warehouse.getModifiedProducts();
    products.forEach(System.out::println);
  }

  private static void viewProductsCountByCategory(Warehouse warehouse) {
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

  private static void viewProductsGroupedByFirstLetter(Warehouse warehouse) {
    Map<Character, Long> productCounts = warehouse.numberOfProductsWithSameFirstLetter();
    productCounts.forEach((initial, count) ->
            System.out.printf("Initial: %c, Count: %d%n", initial, count)
    );
  }
}


