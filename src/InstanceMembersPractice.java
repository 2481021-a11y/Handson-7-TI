import java.util.*;

public class InstanceMembersPractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Instance Variables & Methods
         */

        // ===== INSTANCE VARIABLES BASICS =====
        System.out.println("=== INSTANCE VARIABLES BASICS ===");

        // Buat 3 object BankAccount dengan data berbeda
        BankAccount acc1 = new BankAccount("Juan", "Savings", 1000000);
        BankAccount acc2 = new BankAccount("Niko", "Checking", 500000);
        BankAccount acc3 = new BankAccount("Lisal", "Savings", 250000);

        // Tampilkan saldo masing-masing account
        acc1.displayBalance();
        acc2.displayBalance();
        acc3.displayBalance();

        // Lakukan transaksi pada masing-masing account
        acc1.deposit(500000);
        acc2.withdraw(200000);
        acc3.deposit(100000);

        System.out.println("\nSetelah transaksi:");
        acc1.displayBalance();
        acc2.displayBalance();
        acc3.displayBalance();

        // Tunjukkan bahwa perubahan pada satu object tidak mempengaruhi yang lain
        System.out.println("\nSaldo acc1 tetap tidak mempengaruhi acc2 dan acc3.");

        // ===== INSTANCE METHODS ADVANCED =====
        System.out.println("\n=== INSTANCE METHODS ADVANCED ===");
        acc1.calculateInterest(3.5);
        acc2.getAccountInfo();

        // ===== METHOD INTERACTION =====
        System.out.println("\n=== METHOD INTERACTION ===");
        acc1.printStatement();

        // ===== OBJECT COLLABORATION =====
        System.out.println("\n=== OBJECT COLLABORATION ===");
        acc1.transfer(acc2, 300000); // Transfer dari acc1 ke acc2
        acc2.transfer(acc3, 100000); // Transfer dari acc2 ke acc3

        System.out.println("\nSetelah transfer:");
        acc1.displayBalance();
        acc2.displayBalance();
        acc3.displayBalance();

        // Cek history transaksi acc1
        System.out.println("\n=== TRANSACTION HISTORY JUAN ===");
        acc1.printStatement();

        // Buat customer yang punya banyak account
        Customer customer = new Customer("C001", "Juan Bagas", "08123456789", "juan@example.com");
        customer.addAccount(acc1);
        customer.addAccount(acc3);
        customer.showAllAccounts();
    }
}

// ===== CLASS DEFINITIONS =====
class BankAccount {
    private static int nextAccountNumber = 1001;

    // Instance variables
    private int accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;
    private boolean isActive;
    private ArrayList<Transaction> history;

    // Constructor
    public BankAccount(String accountHolderName, String accountType, double initialDeposit) {
        this.accountNumber = nextAccountNumber++;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.isActive = true;
        this.history = new ArrayList<>();
        history.add(new Transaction("OPEN", initialDeposit, "Akun baru dibuka"));
    }

    // Instance methods
    public void deposit(double amount) {
        if (amount > 0 && isActive) {
            balance += amount;
            history.add(new Transaction("DEPOSIT", amount, "Setoran tunai"));
            System.out.println(accountHolderName + " menyetor Rp" + amount);
        } else {
            System.out.println("Setoran gagal!");
        }
    }

    public void withdraw(double amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
            history.add(new Transaction("WITHDRAW", amount, "Penarikan tunai"));
            System.out.println(accountHolderName + " menarik Rp" + amount);
        } else {
            System.out.println("Penarikan gagal untuk " + accountHolderName);
        }
    }

    public void transfer(BankAccount target, double amount) {
        if (canWithdraw(amount)) {
            this.withdraw(amount);
            target.deposit(amount);
            history.add(new Transaction("TRANSFER", amount, "Transfer ke " + target.accountHolderName));
            System.out.println("Transfer Rp" + amount + " dari " + this.accountHolderName + " ke " + target.accountHolderName);
        } else {
            System.out.println("Transfer gagal: saldo tidak cukup atau akun tidak aktif.");
        }
    }

    public boolean canWithdraw(double amount) {
        return amount > 0 && amount <= balance && isActive;
    }

    public void calculateInterest(double rate) {
        double interest = (balance * rate) / 100;
        System.out.println(accountHolderName + " mendapatkan bunga Rp" + interest + " dari saldo Rp" + balance);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountStatus() {
        return isActive ? "Aktif" : "Nonaktif";
    }

    public void activateAccount() {
        isActive = true;
        System.out.println(accountHolderName + ": Akun diaktifkan.");
    }

    public void deactivateAccount() {
        isActive = false;
        System.out.println(accountHolderName + ": Akun dinonaktifkan.");
    }

    public void displayBalance() {
        System.out.println("Akun " + accountHolderName + " (" + accountNumber + "): Rp" + balance);
    }

    public void getAccountInfo() {
        System.out.println("=== Info Akun ===");
        System.out.println("No Akun: " + accountNumber);
        System.out.println("Nama: " + accountHolderName);
        System.out.println("Tipe: " + accountType);
        System.out.println("Saldo: Rp" + balance);
        System.out.println("Status: " + getAccountStatus());
    }

    public void printStatement() {
        System.out.println("=== Riwayat Transaksi Akun " + accountHolderName + " ===");
        for (Transaction t : history) {
            t.printTransaction();
        }
    }
}

// ===== CLASS TRANSACTION =====
class Transaction {
    private static int nextId = 1;
    private int transactionId;
    private String type;
    private double amount;
    private Date timestamp;
    private String description;

    public Transaction(String type, double amount, String description) {
        this.transactionId = nextId++;
        this.type = type;
        this.amount = amount;
        this.timestamp = new Date();
        this.description = description;
    }

    public void printTransaction() {
        System.out.println("#" + transactionId + " [" + type + "] Rp" + amount + " - " + description + " (" + timestamp + ")");
    }
}

// ===== CLASS CUSTOMER =====
class Customer {
    private String customerId;
    private String name;
    private String phone;
    private String email;
    private ArrayList<BankAccount> accounts;

    public Customer(String customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount acc) {
        accounts.add(acc);
        System.out.println("Akun milik " + name + " ditambahkan ke daftar nasabah.");
    }

    public void showAllAccounts() {
        System.out.println("\n=== Daftar Akun Nasabah: " + name + " ===");
        for (BankAccount acc : accounts) {
            acc.displayBalance();
        }
    }
}