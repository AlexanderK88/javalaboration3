# Warehouse Management System

Welcome to the **Warehouse Management System** project! This application is designed to help manage products in a warehouse, offering functionalities such as adding new products, modifying existing ones, and viewing products based on various criteria.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [Usage](#usage)
- [Testing](#testing)

## Overview

The Warehouse Management System is a console-based Java application that allows users to manage a collection of products within a warehouse. It supports operations like listing all products, adding new products, modifying existing products, and querying products based on categories, IDs, creation dates, and more.

## Features

- **List All Products**: View a list of all products currently in the warehouse.
- **Add New Product**: Add a new product with details like ID, name, category, rating, and creation date.
- **Modify Product**: Modify an existing product's category, rating, or name.
- **View Products by Category**: Filter and view products belonging to a specific category.
- **View Product by ID**: Retrieve details of a product using its unique ID.
- **View Products by Creation Date**: List products created after a specific date.
- **View Modified Products**: Display products that have been modified since their creation.
- **Product Count by Category**: Show the number of products in a specific category.
- **Categories with Products**: List all categories that have at least one product.
- **Products Grouped by First Letter**: Count how many products start with the same letter.
- **Products with Max Rating This Month**: Get products with the highest rating created this month.

## Technologies Used

- **Java 17**: The core programming language used for the application.
- **JUnit 5**: For writing and running unit tests.
- **Mockito**: For mocking dependencies in tests.
- **AssertJ**: For fluent assertions in tests.
- **Maven**: Build automation and dependency management.

## Project Structure

```
├── src
│   ├── main
│   │   └── java
│   │       ├── org.example
│   │       │   ├── App.java
│   │       │   └── entities
│   │       │       ├── Category.java
│   │       │       ├── Product.java
│   │       │       ├── ProductRecord.java
│   │       │       └── PopulateWarehouse.java
│   │       └── org.example.service
│   │           └── Warehouse.java
│   └── test
│       └── java
│           └── org.example.service
│               └── WarehouseTest.java
├── pom.xml
└── README.md
```

- **App.java**: The main application class containing the entry point and user interface logic.
- **Warehouse.java**: Contains the business logic for managing products.
- **entities**: Package containing data models like `Product`, `ProductRecord`, and `Category`.
- **WarehouseTest.java**: Contains unit tests for the `Warehouse` class.
- **pom.xml**: Maven configuration file for dependencies and build instructions.

## Setup and Installation

### Prerequisites

- **Java Development Kit (JDK) 17** or higher.
- **Apache Maven** 3.6 or higher.
- A Java-compatible IDE (e.g., IntelliJ IDEA, Eclipse) is recommended but not required.

### Installation Steps

1. **Clone the Repository**

   ```bash
   git clone https://github.com/AlexanderK88/javalaboration3.git
   cd javalaboration3
   ```

2. **Build the Project**

   Use Maven to compile the project and download dependencies:

   ```bash
   mvn clean compile
   ```

## Running the Application

You can run the application using Maven or directly through your IDE.

### Using Maven

```bash
mvn exec:java -Dexec.mainClass="org.example.App"
```

### Using the IDE

- Open the project in your IDE.
- Navigate to `App.java`.
- Run the `main` method.

## Usage

Upon running the application, you'll be presented with a menu:

```
Warehouse Management Menu:
1. List all products
2. Add a new product
3. Modify a product
4. View products by category
5. View product by ID
6. View products by creation date
7. View products modified since creation
8. Show amount of products in a category
9. Get all categories with at least 1 product
10. Count how many products start with the same letter
11. Get products with max rating this month
12. Exit
Enter your choice:
```

### Menu Options

1. **List All Products**

   - Displays all products in the warehouse.
   - Example Output:

     ```
     ProductRecord{id='1', name='Laptop', category=ELECTRONICS, rating=9, creationDate=2024-03-01, lastModified=2024-03-01}
     ```

2. **Add a New Product**

   - Prompts for product details:
     - Name
     - Category (must be one of the predefined categories)
     - Rating (1-10)
   - Adds the product to the warehouse.

3. **Modify a Product**

   - Prompts for the product ID.
   - If the product exists, allows modification of:
     - Category
     - Rating
     - Name

4. **View Products by Category**

   - Prompts for a category.
   - Displays all products in that category.

5. **View Product by ID**

   - Prompts for a product ID.
   - Displays the product if found.

6. **View Products by Creation Date**

   - Prompts for a date (YYYY-MM-DD).
   - Displays products created after the specified date.

7. **View Products Modified Since Creation**

   - Lists products where the last modified date is different from the creation date.

8. **Show Amount of Products in a Category**

   - Prompts for a category.
   - Displays the count of products in that category.

9. **Get All Categories with at Least 1 Product**

   - Lists all categories that have at least one product.

10. **Count How Many Products Start with the Same Letter**

    - Displays a count of products grouped by the first letter of their names.

11. **Get Products with Max Rating This Month**

    - Displays products with the highest rating created in the current month.

12. **Exit**

    - Exits the application.

### Example Usage

**Adding a New Product**

```
Enter your choice: 2
Enter product name: Smartphone
Enter product category (ELECTRONICS, FURNITURE, etc.): ELECTRONICS
Enter product rating 1-10: 8
Added product successfully.
```

**Modifying a Product**

```
Enter your choice: 3
Enter the ID of the product you want to modify: 1
What would you like to modify?
1. Category
2. Rating
3. Name
Enter your choice: 2
Enter the new rating (1-10): 9
Product modified successfully.
```

## Testing

Unit tests are written using JUnit 5, Mockito, and AssertJ.

### Running Tests

```bash
mvn test
```

### Test Coverage

- **WarehouseTest.java**: Contains comprehensive tests for the `Warehouse` class methods, including edge cases and error handling.

### Writing Tests

- Tests are organized using `@Nested` classes for better structure.
- Common assertions are placed in a `CommonAssertions` class for reuse.

---
