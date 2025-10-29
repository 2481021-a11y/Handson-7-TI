import java.time.LocalDate;
import java.util.ArrayList;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Comprehensive OOP Project
         *
         * Instruksi: Implementasikan sistem manajemen perpustakaan lengkap
         * yang menerapkan semua konsep OOP yang telah dipelajari.
         */

        // ===== SETUP LIBRARY SYSTEM =====
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===");

        // Latihan 1: Setup library dengan berbagai tipe buku dan member
        // Buat library instance
        // Tambahkan berbagai buku
        // Daftarkan beberapa member

        // ===== BOOK OPERATIONS =====
        System.out.println("\\n=== BOOK OPERATIONS ===");

        // Latihan 2: Operasi buku
        // Tambah buku baru
        // Cari buku berdasarkan kriteria
        // Update informasi buku

        // ===== MEMBER OPERATIONS =====
        System.out.println("\\n=== MEMBER OPERATIONS ===");

        // Latihan 3: Operasi member
        // Registrasi member baru
        // Update informasi member
        // Cek status member

        // ===== BORROWING SYSTEM =====
        System.out.println("\\n=== BORROWING SYSTEM ===");

        // Latihan 4: Sistem peminjaman
        // Pinjam buku
        // Kembalikan buku
        // Cek denda
        // Perpanjang peminjaman

        // ===== REPORTING =====
        System.out.println("\\n=== REPORTING ===");

        // Latihan 5: Sistem reporting
        // Laporan buku terpopuler
        // Laporan member aktif
        // Laporan denda
        // Statistik perpustakaan

        // ===== ADMIN FUNCTIONS =====
        System.out.println("\\n=== ADMIN FUNCTIONS ===");

        // Latihan 6: Fungsi admin
        // Backup data
        // Generate reports
        // System maintenance
    }
}

// ===== CLASS DEFINITIONS =====

// Implementasikan class Book dengan encapsulation lengkap
class Book {
    // Private instance variables
    // isbn, title, author, publisher, yearPublished, category, isAvailable, borrowCount

    // Static variables
    // static int totalBooks

    // Constructors dengan overloading
    // Multiple constructors

    // Getter/Setter dengan validation
    // All getters and setters dengan appropriate validation

    // Business methods
    // borrowBook(), returnBook(), getPopularityScore()

    // Utility methods
    // displayBookInfo(), isClassic(), getAgeInYears()
}

// Implementasikan class Member
class Member {
    // Private instance variables
    // memberId, name, email, phone, address, joinDate, membershipType, isActive

    // Static variables
    // static int totalMembers, static final int MAX_BOOKS_ALLOWED

    // Constructors
    // Constructor dengan validation

    // Getter/Setter
    // With appropriate validation

    // Business methods
    // canBorrowMore(), calculateMembershipDuration(), upgradeMembership()
}

// Implementasikan class BorrowRecord
class BorrowRecord {
    // Private variables
    // recordId, memberId, isbn, borrowDate, dueDate, returnDate, fine

    // Static variables
    // static int totalRecords

    // Constructor
    // Constructor dengan auto-calculation due date

    // Methods
    // calculateFine(), isOverdue(), returnBook(), extendLoan()
}

// Implementasikan class Library
class Library {
    // Private variables untuk collections
    // ArrayList<Book> books, ArrayList<Member> members, ArrayList<BorrowRecord> borrowRecords

    // Static variables
    // static String libraryName, static final double FINE_PER_DAY

    // Private variables untuk business logic
    // maxBooksPerMember, loanPeriodDays

    // Constructor
    // Initialize collections dan set library parameters

    // Book management methods
    // addBook(), removeBook(), searchBooks(), getAvailableBooks()

    // Member management methods
    // registerMember(), updateMember(), getMemberById(), getActiveMembers()

    // Borrowing methods
    // borrowBook(), returnBook(), extendLoan(), calculateFine()

    // Reporting methods
    // generatePopularBooksReport(), generateActiveMembersReport(), generateOverdueReport()

    // Utility methods
    // displayLibraryStats(), backup(), maintenance()

    // Private helper methods
    // private boolean isValidMember(), private boolean isBookAvailable()
}

// Implementasikan class LibraryUtils (static utility class)
class LibraryUtils {
    // Constants
    // static final variables untuk berbagai konstanta

    // Static utility methods
    // static boolean isValidISBN(), static boolean isValidEmail()
    // static String formatDate(), static double calculateLateFee()

    // Private constructor
    // private LibraryUtils() { }
}

// Implementasikan enum untuk BookCategory
enum BookCategory {
    // FICTION, NON_FICTION, SCIENCE, HISTORY, BIOGRAPHY, etc.
}

// Implementasikan enum untuk MembershipType
enum MembershipType {
    // STUDENT, FACULTY, PUBLIC dengan berbagai privileges
}
