import java.util.Random;

public class InstanceMembersPractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Instance Variables & Methods
         *
         * Instruksi: Lengkapi semua latihan di bawah ini untuk menguasai
         * instance variables, instance methods, dan perbedaannya dengan static.
         */

        // ===== INSTANCE VARIABLES BASICS =====
        System.out.println("=== INSTANCE VARIABLES BASICS ===");

        // Latihan 1: Memahami instance variables
        // Buat beberapa object dari class BankAccount
        // Tunjukkan bahwa setiap object memiliki data sendiri

        // Buat 3 object BankAccount dengan data berbeda

        // Tampilkan saldo masing-masing account

        // Lakukan transaksi pada masing-masing account

        // Tunjukkan bahwa perubahan pada satu object tidak mempengaruhi yang lain

        // ===== INSTANCE METHODS ADVANCED =====
        System.out.println("\\n=== INSTANCE METHODS ADVANCED ===");

        // Latihan 2: Instance methods yang bekerja dengan instance variables
        // Implementasikan berbagai jenis instance methods
        // Methods yang mengubah state object
        // Methods yang mengembalikan nilai berdasarkan state
        // Methods yang berinteraksi dengan object lain

        // Demonstrasikan berbagai jenis instance methods

        // ===== METHOD INTERACTION =====
        System.out.println("\\n=== METHOD INTERACTION ===");

        // Latihan 3: Methods yang memanggil methods lain
        // Buat methods yang memanggil methods lain dalam class yang sama
        // Demonstrasikan method chaining

        // Implementasikan method chaining dan interaction

        // ===== OBJECT COLLABORATION =====
        System.out.println("\\n=== OBJECT COLLABORATION ===");

        // Latihan 4: Object yang berinteraksi dengan object lain
        // Implementasikan transfer uang antar account
        // Buat history transaksi

        // Implementasikan object collaboration
    }
}

// ===== CLASS DEFINITIONS =====

// Implementasikan class BankAccount
class BankAccount {
    // Instance variables
    // accountNumber, accountHolderName, balance, accountType, isActive

    // Constructor(s)
    // Buat multiple constructors

    // Instance methods untuk account operations
    // deposit(double amount)
    // withdraw(double amount) - with validation
    // transfer(BankAccount target, double amount)
    // getBalance()
    // getAccountInfo()
    // activateAccount() / deactivateAccount()

    // Instance methods untuk business logic
    // calculateInterest(double rate)
    // canWithdraw(double amount)
    // getAccountStatus()

    // Instance methods untuk formatting/display
    // displayBalance()
    // printStatement()
}

// Implementasikan class Transaction (untuk history)
class Transaction {
    // transactionId, type, amount, timestamp, description

    // Constructor dan methods
}

// Implementasikan class Customer
class Customer {
    // customerId, name, phone, email, accounts[]

    // Methods untuk mengelola multiple accounts
}
