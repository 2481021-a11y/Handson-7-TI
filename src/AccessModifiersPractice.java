public class AccessModifiersPractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Access Modifiers & Encapsulation
         *
         * Instruksi: Lengkapi semua latihan di bawah ini untuk menguasai
         * access modifiers, encapsulation, dan data protection.
         */

        // ===== MASALAH DENGAN PUBLIC VARIABLES =====
        System.out.println("=== MASALAH DENGAN PUBLIC VARIABLES ===");

        // Latihan 1: Tunjukkan masalah dengan public instance variables
        // Buat object dengan public variables
        // Tunjukkan bagaimana data bisa dirusak dari luar

        // Demonstrasikan masalah public variables

        // ===== ENCAPSULATION SOLUTION =====
        System.out.println("\\n=== ENCAPSULATION SOLUTION ===");

        // Latihan 2: Implementasi encapsulation
        // Gunakan private variables dengan getter/setter
        // Tambahkan validasi dalam setter

        // Demonstrasikan encapsulation dengan class yang proper

        // ===== ACCESS MODIFIER COMBINATIONS =====
        System.out.println("\\n=== ACCESS MODIFIER COMBINATIONS ===");

        // Latihan 3: Berbagai kombinasi access modifiers
        // Tunjukkan perbedaan private, public, protected, default

        // Implementasikan class dengan berbagai access modifiers

        // ===== GETTER/SETTER BEST PRACTICES =====
        System.out.println("\\n=== GETTER/SETTER BEST PRACTICES ===");

        // Latihan 4: Implementasi getter/setter yang proper
        // Naming conventions
        // Validation dalam setter
        // Read-only dan write-only properties

        // Implementasikan getter/setter yang proper
    }
}

// ===== CLASS DEFINITIONS =====

// Implementasikan class BadExample (dengan public variables)
class BadExample {
    // Semua variables public - tunjukkan masalahnya
    public String name;
    public int age;
    public double salary;
    public String email;

    // Constructor sederhana

    // Method untuk menunjukkan data corruption
}

// Implementasikan class GoodExample (dengan encapsulation)
class GoodExample {
    // Private instance variables
    // private String name, age, salary, email

    // Constructor
    // Constructor dengan parameter validation

    // Getter methods
    // Implementasikan getter methods yang proper

    // Setter methods dengan validation
    // setName() - tidak boleh null/kosong
    // setAge() - harus antara 17-65
    // setSalary() - harus positif
    // setEmail() - format email yang valid

    // Business methods
    // validateEmail(String email)
    // isRetirementAge()
    // calculateTax()
}

// Implementasikan class BankAccountSecure
class BankAccountSecure {
    // Private variables
    // accountNumber, balance, pin, isLocked

    // Constructor
    // Constructor dengan validation

    // Public interface methods
    // deposit(double amount)
    // withdraw(double amount, String pin)
    // checkBalance(String pin)
    // changePin(String oldPin, String newPin)

    // Private helper methods
    // private boolean validatePin(String pin)
    // private void lockAccount()
    // private boolean isValidAmount(double amount)

    // Read-only properties
    // getAccountNumber() - tanpa setter
    // getAccountStatus()

    // Write-only properties (jarang digunakan)
    // setSecurityLevel(int level) - tanpa getter
}

// Implementasikan class AccessModifierDemo
class AccessModifierDemo {
    private String privateField = "Private";
    protected String protectedField = "Protected";
    String defaultField = "Default";
    public String publicField = "Public";

    private void privateMethod() {
        System.out.println("Private method called");
    }

    protected void protectedMethod() {
        System.out.println("Protected method called");
    }

    void defaultMethod() {
        System.out.println("Default method called");
    }

    public void publicMethod() {
        System.out.println("Public method called");
    }

    // Method untuk test accessibility dari dalam class
    public void testAccess() {
        // Panggil semua methods dan akses semua fields
    }
}
