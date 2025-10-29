public class StaticVsInstancePractice {
    public static void main(String[] args) {

        // ===== STATIC VARIABLES DEMO =====
        System.out.println("=== STATIC VARIABLES DEMO ===");

        Counter c1 = new Counter("Counter A");
        Counter c2 = new Counter("Counter B");
        Counter c3 = new Counter("Counter C");

        c1.displayCounterInfo();
        c2.displayCounterInfo();
        c3.displayCounterInfo();

        System.out.println("Global count (shared): " + Counter.getGlobalCount());

        // ===== STATIC METHODS DEMO =====
        System.out.println("\n=== STATIC METHODS DEMO ===");

        double area = MathUtils.calculateCircleArea(5);
        System.out.println("Area of circle radius 5: " + area);

        double dist = MathUtils.calculateDistance(0, 0, 3, 4);
        System.out.println("Distance between (0,0) and (3,4): " + dist);

        System.out.println("Is 13 prime? " + MathUtils.isPrime(13));
        System.out.println("Factorial 5: " + MathUtils.factorial(5));
        System.out.println("2^8 = " + MathUtils.power(2, 8));

        // ===== STATIC VS INSTANCE COMPARISON =====
        System.out.println("\n=== STATIC VS INSTANCE COMPARISON ===");

        Counter counterA = new Counter("Instance 1");
        counterA.incrementInstance();
        counterA.incrementInstance();
        System.out.println("Instance count (A): " + counterA.getInstanceCount());

        Counter counterB = new Counter("Instance 2");
        System.out.println("Instance count (B): " + counterB.getInstanceCount());
        System.out.println("Shared global count (all): " + Counter.getGlobalCount());

        // ===== STATIC INITIALIZATION =====
        System.out.println("\n=== STATIC INITIALIZATION ===");

        System.out.println("Static block of DatabaseConnection runs before any instance is created.");
        DatabaseConnection conn1 = DatabaseConnection.getConnection();
        DatabaseConnection conn2 = DatabaseConnection.getConnection();
        conn1.executeQuery("SELECT * FROM users");
        conn2.disconnect();

        System.out.println("Active connections: " + DatabaseConnection.getActiveConnectionCount());
        DatabaseConnection.closeAllConnections();

        // ===== BEST PRACTICES =====
        System.out.println("\n=== BEST PRACTICES ===");

        Student.setUniversityName("Universitas Indonesia");
        Student s1 = new Student("S001", "Andi", "Informatika", 3.8);
        Student s2 = new Student("S002", "Budi", "Ekonomi", 3.4);

        s1.displayStudentInfo();
        s2.displayStudentInfo();

        System.out.println("Total students: " + Student.getTotalStudents());
    }
}

// ===== CLASS DEFINITIONS =====

// CLASS 1: Counter (demonstrasi static vs instance variable)
class Counter {
    // Static variables
    private static int globalCount = 0;
    public static final String APP_NAME = "CounterApp";

    // Instance variables
    private int instanceCount;
    private String counterName;

    // Static initialization block
    static {
        System.out.println("[Static block] Counter class loaded. App Name: " + APP_NAME);
    }

    // Constructor
    public Counter(String counterName) {
        this.counterName = counterName;
        instanceCount++;
        globalCount++;
        System.out.println("Counter object created: " + counterName);
    }

    // Static methods
    public static int getGlobalCount() {
        return globalCount;
    }

    public static void resetGlobalCount() {
        globalCount = 0;
    }

    public static void displayAppInfo() {
        System.out.println("App: " + APP_NAME + " | Global count: " + globalCount);
    }

    // Instance methods
    public int getInstanceCount() {
        return instanceCount;
    }

    public void incrementInstance() {
        instanceCount++;
        globalCount++;
    }

    public void displayCounterInfo() {
        System.out.println(counterName + " → Instance count: " + instanceCount + ", Global count: " + globalCount);
    }
}

// CLASS 2: MathUtils (static utility class)
class MathUtils {
    public static final double PI = 3.14159;
    public static final double E = 2.71828;

    private MathUtils() {
        // Prevent instantiation
    }

    public static double calculateCircleArea(double radius) {
        return PI * radius * radius;
    }

    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        int result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    public static double power(double base, int exponent) {
        double result = 1;
        for (int i = 0; i < exponent; i++) result *= base;
        return result;
    }
}

// CLASS 3: DatabaseConnection (static connection pool)
class DatabaseConnection {
    private static int maxConnections = 3;
    private static int activeConnections = 0;
    private static boolean isInitialized;

    private String connectionId;
    private boolean isConnected;
    private String database = "MainDB";

    // Static initialization block
    static {
        isInitialized = true;
        System.out.println("[Static block] DatabaseConnection pool initialized (max = " + maxConnections + ")");
    }

    private DatabaseConnection(String connectionId) {
        this.connectionId = connectionId;
        this.isConnected = true;
        activeConnections++;
        System.out.println("✅ Connection created: " + connectionId);
    }

    // Static factory method
    public static DatabaseConnection getConnection() {
        if (activeConnections >= maxConnections) {
            System.out.println("⚠ Tidak bisa membuat koneksi baru (pool penuh).");
            return null;
        }
        return new DatabaseConnection("CONN-" + (activeConnections + 1));
    }

    public static void closeAllConnections() {
        System.out.println("🔒 Semua koneksi ditutup.");
        activeConnections = 0;
    }

    public static int getActiveConnectionCount() {
        return activeConnections;
    }

    // Instance methods
    public void connect() {
        if (!isConnected) {
            isConnected = true;
            activeConnections++;
            System.out.println(connectionId + " reconnected.");
        }
    }

    public void disconnect() {
        if (isConnected) {
            isConnected = false;
            activeConnections--;
            System.out.println(connectionId + " disconnected.");
        }
    }

    public void executeQuery(String query) {
        if (isConnected) {
            System.out.println(connectionId + " executing: " + query);
        } else {
            System.out.println(connectionId + " not connected.");
        }
    }
}

// CLASS 4: Student (mix static & instance)
class Student {
    private static String universityName;
    private static int totalStudents = 0;

    private String studentId;
    private String name;
    private String major;
    private double gpa;

    public Student(String studentId, String name, String major, double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.gpa = gpa;
        totalStudents++;
    }

    // Static methods
    public static int getTotalStudents() {
        return totalStudents;
    }

    public static void setUniversityName(String name) {
        universityName = name;
    }

    public static String getUniversityName() {
        return universityName;
    }

    // Instance methods
    public void updateGPA(double newGPA) {
        if (newGPA >= 0 && newGPA <= 4.0) {
            this.gpa = newGPA;
        } else {
            System.out.println("Invalid GPA!");
        }
    }

    public void displayStudentInfo() {
        System.out.println(name + " (" + studentId + ") - " + major + " | GPA: " + gpa + " | " + universityName);
    }

    public boolean isHonorStudent() {
        return gpa >= 3.5;
    }
}