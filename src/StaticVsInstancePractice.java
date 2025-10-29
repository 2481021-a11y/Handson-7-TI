public class StaticVsInstancePractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Static vs Instance
         *
         * Instruksi: Lengkapi semua latihan di bawah ini untuk menguasai
         * perbedaan static dan instance members, kapan menggunakan masing-masing.
         */

        // ===== STATIC VARIABLES DEMO =====
        System.out.println("=== STATIC VARIABLES DEMO ===");

        // Latihan 1: Static variables sebagai shared data
        // Buat beberapa object dari class yang memiliki static counter
        // Tunjukkan bahwa static variable di-share oleh semua object

        // Demonstrasikan static variable sharing

        // ===== STATIC METHODS DEMO =====
        System.out.println("\\n=== STATIC METHODS DEMO ===");

        // Latihan 2: Static methods sebagai utility functions
        // Implementasikan utility methods yang tidak butuh object
        // Tunjukkan cara memanggil static methods

        // Demonstrasikan static methods usage

        // ===== STATIC VS INSTANCE COMPARISON =====
        System.out.println("\\n=== STATIC VS INSTANCE COMPARISON ===");

        // Latihan 3: Perbandingan langsung static vs instance
        // Tunjukkan memory usage difference
        // Performance comparison

        // Implementasikan perbandingan

        // ===== STATIC INITIALIZATION =====
        System.out.println("\\n=== STATIC INITIALIZATION ===");

        // Latihan 4: Static initialization blocks
        // Tunjukkan kapan static variables diinisialisasi
        // Static blocks vs static variable initialization

        // Demonstrasikan static initialization

        // ===== BEST PRACTICES =====
        System.out.println("\\n=== BEST PRACTICES ===");

        // Latihan 5: Kapan menggunakan static vs instance
        // Constants (static final)
        // Utility methods (static)
        // Counters/global state (static)
        // Object-specific data (instance)

        // Implementasikan best practices examples
    }
}

// ===== CLASS DEFINITIONS =====

// Implementasikan class Counter dengan static dan instance counters
class Counter {
    // Static variables
    // static int globalCount
    // static final String APP_NAME

    // Instance variables
    // int instanceCount
    // String counterName

    // Static initialization block
    // static { ... }

    // Constructor
    // Increment both static dan instance counters

    // Static methods
    // static int getGlobalCount()
    // static void resetGlobalCount()
    // static void displayAppInfo()

    // Instance methods
    // int getInstanceCount()
    // void incrementInstance()
    // void displayCounterInfo()
}

// Implementasikan class MathUtils dengan static utility methods
class MathUtils {
    // Constants
    // static final double PI
    // static final double E

    // Static utility methods
    // static double calculateCircleArea(double radius)
    // static double calculateDistance(double x1, double y1, double x2, double y2)
    // static boolean isPrime(int number)
    // static int factorial(int n)
    // static double power(double base, int exponent)

    // Private constructor (utility class)
    // private MathUtils() { ... }
}

// Implementasikan class DatabaseConnection dengan static connection pool
class DatabaseConnection {
    // Static variables untuk connection pool
    // static int maxConnections
    // static int activeConnections
    // static boolean isInitialized

    // Instance variables
    // String connectionId
    // boolean isConnected
    // String database

    // Static initialization
    // static block untuk setup connection pool

    // Static methods untuk connection management
    // static DatabaseConnection getConnection()
    // static void closeAllConnections()
    // static int getActiveConnectionCount()

    // Instance methods
    // void connect()
    // void disconnect()
    // void executeQuery(String query)
}

// Implementasikan class Student dengan mixed static/instance
class Student {
    // Static variables
    // static String universityName
    // static int totalStudents

    // Instance variables
    // String studentId, name, major
    // double gpa

    // Constructor
    // Increment totalStudents

    // Static methods
    // static int getTotalStudents()
    // static void setUniversityName(String name)
    // static String getUniversityName()

    // Instance methods
    // void updateGPA(double newGPA)
    // void displayStudentInfo()
    // boolean isHonorStudent()
}
