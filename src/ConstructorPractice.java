import java.time.LocalDate;

public class ConstructorPractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Constructors
         *
         * Instruksi: Lengkapi semua latihan di bawah ini untuk menguasai
         * constructors, constructor overloading, dan keyword this.
         */

        // ===== DEFAULT VS CUSTOM CONSTRUCTOR =====
        System.out.println("=== DEFAULT VS CUSTOM CONSTRUCTOR ===");

        // Latihan 1: Memahami default constructor
        // Buat class dengan dan tanpa custom constructor
        // Tunjukkan perbedaan penggunaan default constructor

        // Demonstrasikan penggunaan default constructor

        // ===== CONSTRUCTOR OVERLOADING =====
        System.out.println("\\n=== CONSTRUCTOR OVERLOADING ===");

        // Latihan 2: Multiple constructors untuk berbagai skenario
        // Implementasikan class Product dengan multiple constructors
        // Setiap constructor untuk kasus penggunaan yang berbeda

        // Buat object Product menggunakan berbagai constructors

        // ===== KEYWORD THIS =====
        System.out.println("\\n=== KEYWORD THIS ===");

        // Latihan 3: Penggunaan this dalam constructor dan methods
        // Demonstrasikan this untuk menghindari name collision
        // Gunakan this untuk memanggil constructor lain
        // Gunakan this untuk mereferensikan current object

        // Demonstrasikan berbagai penggunaan this

        // ===== CONSTRUCTOR CHAINING =====
        System.out.println("\\n=== CONSTRUCTOR CHAINING ===");

        // Latihan 4: Constructor yang memanggil constructor lain
        // Implementasikan constructor chaining dengan this()
        // Tunjukkan keuntungan constructor chaining

        // Implementasikan constructor chaining

        // ===== INITIALIZATION ORDER =====
        System.out.println("\\n=== INITIALIZATION ORDER ===");

        // Latihan 5: Urutan inisialisasi object
        // Tunjukkan urutan eksekusi saat object dibuat
        // Instance variable initialization vs constructor

        // Demonstrasikan urutan inisialisasi
    }
}

// ===== CLASS DEFINITIONS =====

// Implementasikan class SimpleClass (tanpa custom constructor)
class SimpleClass {
    // Hanya instance variables, tanpa constructor
}

// Implementasikan class Product dengan constructor overloading
class Product {
    // Instance variables
    // productId, name, description, price, category, inStock, supplier

    // Constructor 1: Full parameters
    // public Product(String productId, String name, String description, double price, String category, int inStock, String supplier)

    // Constructor 2: Essential parameters only
    // public Product(String productId, String name, double price)

    // Constructor 3: Copy constructor
    // public Product(Product other)

    // Constructor 4: Default constructor with default values
    // public Product()

    // Methods
    // displayProductInfo()
    // updateStock(int quantity)
    // applyDiscount(double percentage)
    // isAvailable()
}

// Implementasikan class Employee dengan constructor chaining
class Employee {
    // Instance variables
    // employeeId, firstName, lastName, department, position, salary, hireDate

    // Constructor chaining examples
    // Buat multiple constructors yang saling memanggil dengan this()

    // Methods
    // getFullName()
    // calculateYearsOfService()
    // getEmployeeInfo()
    // giveRaise(double percentage)
}

// Implementasikan class InitializationDemo
class InitializationDemo {
    // Tunjukkan instance variable initialization
    // Tunjukkan urutan eksekusi constructor
    // Tambahkan System.out.println di berbagai tempat untuk tracking
}
