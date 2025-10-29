import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ConstructorPractice {
    public static void main(String[] args) {

        // ===== DEFAULT VS CUSTOM CONSTRUCTOR =====
        System.out.println("=== DEFAULT VS CUSTOM CONSTRUCTOR ===");

        // SimpleClass: no constructor → Java provides default one
        SimpleClass obj1 = new SimpleClass();
        System.out.println("SimpleClass created with default constructor.\n");

        // Product with custom constructor
        Product p1 = new Product("P001", "Laptop", "Gaming laptop", 1500.0, "Electronics", 10, "TechCorp");
        p1.displayProductInfo();

        // ===== CONSTRUCTOR OVERLOADING =====
        System.out.println("\n=== CONSTRUCTOR OVERLOADING ===");

        Product p2 = new Product("P002", "Mouse", 25.0);
        Product p3 = new Product(); // uses default constructor
        Product p4 = new Product(p1); // copy constructor

        p2.displayProductInfo();
        p3.displayProductInfo();
        p4.displayProductInfo();

        // ===== KEYWORD THIS =====
        System.out.println("\n=== KEYWORD THIS ===");

        p2.updateStock(20);
        p2.applyDiscount(10);
        System.out.println("Is product available? " + p2.isAvailable());

        // ===== CONSTRUCTOR CHAINING =====
        System.out.println("\n=== CONSTRUCTOR CHAINING ===");

        Employee e1 = new Employee("E001", "Alice", "Johnson", "IT", "Developer", 75000, LocalDate.of(2020, 3, 15));
        Employee e2 = new Employee("E002", "Bob", "Brown");
        Employee e3 = new Employee("E003");

        e1.getEmployeeInfo();
        e2.getEmployeeInfo();
        e3.getEmployeeInfo();

        e1.giveRaise(10);
        System.out.println("Years of service: " + e1.calculateYearsOfService());

        // ===== INITIALIZATION ORDER =====
        System.out.println("\n=== INITIALIZATION ORDER ===");
        InitializationDemo demo = new InitializationDemo();
    }
}

// ===== CLASS DEFINITIONS =====

// 1. SimpleClass: no constructor → default provided automatically
class SimpleClass {
    int number;
    String text;
}

// 2. Product class with constructor overloading
class Product {
    String productId;
    String name;
    String description;
    double price;
    String category;
    int inStock;
    String supplier;

    // Constructor 1: Full
    public Product(String productId, String name, String description, double price,
                   String category, int inStock, String supplier) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.inStock = inStock;
        this.supplier = supplier;
        System.out.println("Full constructor called for " + this.name);
    }

    // Constructor 2: Essential only
    public Product(String productId, String name, double price) {
        this(productId, name, "No description", price, "General", 0, "Unknown Supplier");
        System.out.println("Essential constructor called for " + this.name);
    }

    // Constructor 3: Copy constructor
    public Product(Product other) {
        this(other.productId, other.name, other.description, other.price, other.category, other.inStock, other.supplier);
        System.out.println("Copy constructor called for " + this.name);
    }

    // Constructor 4: Default
    public Product() {
        this("P000", "Unknown", "No description", 0.0, "Misc", 0, "None");
        System.out.println("Default constructor called.");
    }

    // Methods
    void displayProductInfo() {
        System.out.println("Product ID: " + productId + ", Name: " + name +
                ", Price: $" + price + ", Stock: " + inStock + ", Category: " + category);
    }

    void updateStock(int quantity) {
        this.inStock += quantity;
        System.out.println(this.name + " stock updated. Now in stock: " + this.inStock);
    }

    void applyDiscount(double percentage) {
        this.price -= this.price * (percentage / 100.0);
        System.out.println(this.name + " new price after " + percentage + "% discount: $" + this.price);
    }

    boolean isAvailable() {
        return this.inStock > 0;
    }
}

// 3. Employee class with constructor chaining
class Employee {
    String employeeId;
    String firstName;
    String lastName;
    String department;
    String position;
    double salary;
    LocalDate hireDate;

    // Full constructor
    public Employee(String employeeId, String firstName, String lastName, String department,
                    String position, double salary, LocalDate hireDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
        System.out.println("Full Employee constructor called for " + firstName);
    }

    // Constructor chaining example (calls full constructor)
    public Employee(String employeeId, String firstName, String lastName) {
        this(employeeId, firstName, lastName, "General", "Staff", 40000, LocalDate.now());
        System.out.println("Partial constructor called for " + firstName);
    }

    public Employee(String employeeId) {
        this(employeeId, "Unknown", "Employee");
        System.out.println("Minimal constructor called.");
    }

    // Methods
    String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    long calculateYearsOfService() {
        return ChronoUnit.YEARS.between(this.hireDate, LocalDate.now());
    }

    void getEmployeeInfo() {
        System.out.println("[" + employeeId + "] " + getFullName() + " - " + position + " in " + department +
                ", Salary: $" + salary + ", Hired: " + hireDate);
    }

    void giveRaise(double percentage) {
        this.salary += this.salary * (percentage / 100.0);
        System.out.println(this.firstName + " received a " + percentage + "% raise. New salary: $" + this.salary);
    }
}

// 4. InitializationDemo class
class InitializationDemo {
    static {
        System.out.println("Static block executed first (only once).");
    }

    int value = initializeValue();

    {
        System.out.println("Instance initializer block executed before constructor.");
    }

    public InitializationDemo() {
        System.out.println("Constructor executed after instance variables and blocks.");
        System.out.println("value = " + value);
    }

    private int initializeValue() {
        System.out.println("Instance variable initialization executed.");
        return 42;
    }
}