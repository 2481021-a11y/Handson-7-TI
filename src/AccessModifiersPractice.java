public class AccessModifiersPractice {
    public static void main(String[] args) {

        // ===== MASALAH DENGAN PUBLIC VARIABLES =====
        System.out.println("=== MASALAH DENGAN PUBLIC VARIABLES ===");

        BadExample bad = new BadExample("Andi", 25, 5000.0, "andi@example.com");
        System.out.println("Sebelum: " + bad.name + " - " + bad.age + " - " + bad.salary + " - " + bad.email);

        // Data bisa diubah sembarangan dari luar
        bad.age = -5;
        bad.salary = -100;
        bad.email = "bukanemail";
        System.out.println("Setelah dirusak: " + bad.name + " - " + bad.age + " - " + bad.salary + " - " + bad.email);
        bad.showDataCorruption();

        // ===== ENCAPSULATION SOLUTION =====
        System.out.println("\n=== ENCAPSULATION SOLUTION ===");

        GoodExample good = new GoodExample("Budi", 30, 7000, "budi@example.com");
        good.displayInfo();

        // Coba ubah dengan setter (dengan validasi)
        good.setAge(70); // invalid
        good.setSalary(-5000); // invalid
        good.setEmail("email_salah");
        good.displayInfo();

        // Setter valid
        good.setAge(35);
        good.setSalary(9000);
        good.setEmail("budi.updated@mail.com");
        good.displayInfo();

        // ===== ACCESS MODIFIER COMBINATIONS =====
        System.out.println("\n=== ACCESS MODIFIER COMBINATIONS ===");

        AccessModifierDemo demo = new AccessModifierDemo();
        demo.testAccess(); // dari dalam class

        // Dari luar class
        System.out.println("\nDari main(): hanya bisa akses public:");
        System.out.println(demo.publicField);
        demo.publicMethod();

        // ===== GETTER/SETTER BEST PRACTICES =====
        System.out.println("\n=== GETTER/SETTER BEST PRACTICES ===");

        BankAccountSecure account = new BankAccountSecure("1234567890", 1000.0, "4321");
        account.deposit(500);
        account.checkBalance("4321");
        account.withdraw(300, "4321");
        account.checkBalance("4321");

        // Salah pin
        account.withdraw(100, "0000");
        account.changePin("4321", "1234");
        account.checkBalance("1234");
    }
}

// ===== CLASS DEFINITIONS =====

// CLASS 1: BadExample — tanpa encapsulation
class BadExample {
    public String name;
    public int age;
    public double salary;
    public String email;

    public BadExample(String name, int age, double salary, String email) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.email = email;
    }

    public void showDataCorruption() {
        System.out.println("Data corrupted! age/salary/email tidak tervalidasi.");
    }
}

// CLASS 2: GoodExample — menggunakan encapsulation
class GoodExample {
    private String name;
    private int age;
    private double salary;
    private String email;

    public GoodExample(String name, int age, double salary, String email) {
        setName(name);
        setAge(age);
        setSalary(salary);
        setEmail(email);
    }

    // GETTER METHODS
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    public String getEmail() { return email; }

    // SETTER METHODS DENGAN VALIDASI
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("❌ Nama tidak boleh kosong!");
        } else {
            this.name = name;
        }
    }

    public void setAge(int age) {
        if (age < 17 || age > 65) {
            System.out.println("❌ Umur harus antara 17–65 tahun.");
        } else {
            this.age = age;
        }
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            System.out.println("❌ Gaji harus positif.");
        } else {
            this.salary = salary;
        }
    }

    public void setEmail(String email) {
        if (validateEmail(email)) {
            this.email = email;
        } else {
            System.out.println("❌ Format email tidak valid.");
        }
    }

    // BUSINESS METHODS
    private boolean validateEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean isRetirementAge() {
        return this.age >= 60;
    }

    public double calculateTax() {
        return this.salary * 0.1;
    }

    public void displayInfo() {
        System.out.println(name + " (" + age + " th) - Gaji: " + salary + " - Email: " + email);
    }
}

// CLASS 3: BankAccountSecure — contoh encapsulation + validasi
class BankAccountSecure {
    private String accountNumber;
    private double balance;
    private String pin;
    private boolean isLocked;

    public BankAccountSecure(String accountNumber, double balance, String pin) {
        if (accountNumber == null || accountNumber.isEmpty()) throw new IllegalArgumentException("Account number required");
        if (balance < 0) throw new IllegalArgumentException("Initial balance cannot be negative");
        if (pin == null || pin.length() != 4) throw new IllegalArgumentException("PIN must be 4 digits");

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
        this.isLocked = false;
        System.out.println("✅ Akun bank dibuat: " + accountNumber);
    }

    public void deposit(double amount) {
        if (isValidAmount(amount)) {
            balance += amount;
            System.out.println("✅ Deposit $" + amount + " berhasil. Saldo: $" + balance);
        }
    }

    public void withdraw(double amount, String pin) {
        if (isLocked) {
            System.out.println("❌ Akun terkunci.");
            return;
        }
        if (!validatePin(pin)) {
            lockAccount();
            return;
        }
        if (amount > balance) {
            System.out.println("❌ Saldo tidak cukup.");
        } else if (isValidAmount(amount)) {
            balance -= amount;
            System.out.println("✅ Penarikan $" + amount + " berhasil. Sisa saldo: $" + balance);
        }
    }

    public void checkBalance(String pin) {
        if (validatePin(pin)) {
            System.out.println("💰 Saldo saat ini: $" + balance);
        } else {
            System.out.println("❌ PIN salah.");
        }
    }

    public void changePin(String oldPin, String newPin) {
        if (validatePin(oldPin)) {
            if (newPin.length() == 4) {
                this.pin = newPin;
                System.out.println("✅ PIN berhasil diubah.");
            } else {
                System.out.println("❌ PIN baru harus 4 digit.");
            }
        } else {
            System.out.println("❌ PIN lama salah.");
        }
    }

    private boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    private void lockAccount() {
        this.isLocked = true;
        System.out.println("🚫 Akun terkunci karena PIN salah.");
    }

    private boolean isValidAmount(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Jumlah tidak valid.");
            return false;
        }
        return true;
    }

    // Read-only properties
    public String getAccountNumber() { return accountNumber; }
    public boolean getAccountStatus() { return !isLocked; }

    // Write-only example
    public void setSecurityLevel(int level) {
        System.out.println("🔒 Security level diatur ke " + level);
    }
}

// CLASS 4: AccessModifierDemo — kombinasi access modifiers
class AccessModifierDemo {
    private String privateField = "Private";
    protected String protectedField = "Protected";
    String defaultField = "Default"; // package-private
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

    public void testAccess() {
        System.out.println("Dalam class yang sama:");
        System.out.println(privateField + " | " + protectedField + " | " + defaultField + " | " + publicField);

        privateMethod();
        protectedMethod();
        defaultMethod();
        publicMethod();
    }
}