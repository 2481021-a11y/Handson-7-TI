import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * LibraryManagementSystem.java
 * Kompleksitas sedang: contoh sistem perpustakaan sederhana namun lengkap untuk latihan OOP.
 */
public class LibraryManagementSystem {
    public static void main(String[] args) {
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===");

        // Setup library
        Library lib = new Library("Perpustakaan Kota", 3, 14); // maxBooksPerMember=3, loanPeriodDays=14

        // Tambah buku
        lib.addBook(new Book("978-0140449136", "The Odyssey", "Homer", "Penguin", 1998, BookCategory.HISTORY));
        lib.addBook(new Book("978-0261103573", "The Lord of the Rings", "J.R.R. Tolkien", "HarperCollins", 1995, BookCategory.FICTION));
        lib.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch", "Addison-Wesley", 2018, BookCategory.SCIENCE));
        lib.addBook(new Book("978-0201633610", "Design Patterns", "Gamma et al.", "Addison-Wesley", 1994, BookCategory.SCIENCE));
        lib.addBook(new Book("978-0307269997", "The Road", "Cormac McCarthy", "Vintage", 2007, BookCategory.FICTION));

        // Daftarkan member
        Member m1 = lib.registerMember("Andi", "andi@mail.com", "08123456789", "Jl. Merdeka 1", MembershipType.STUDENT);
        Member m2 = lib.registerMember("Budi", "budi@mail.com", "08129876543", "Jl. Sudirman 2", MembershipType.PUBLIC);

        // Demonstrasi operasi buku: search
        System.out.println("\n-- Pencarian buku 'Java' (tidak ada) --");
        List<Book> search = lib.searchBooks("Java");
        if (search.isEmpty()) System.out.println("Tidak ditemukan.");

        System.out.println("\n-- Pencarian kategori SCIENCE --");
        lib.searchByCategory(BookCategory.SCIENCE).forEach(Book::displayBookInfo);

        // Peminjaman
        System.out.println("\n-- Peminjaman oleh Andi --");
        Book bookToBorrow = lib.findAvailableByISBN("978-0134685991"); // Effective Java
        if (bookToBorrow != null) {
            lib.borrowBook(m1.getMemberId(), bookToBorrow.getIsbn());
        }

        // Attempt to borrow same book by Budi
        System.out.println("\n-- Budi mencoba meminjam buku yang sama --");
        lib.borrowBook(m2.getMemberId(), "978-0134685991");

        // Andi meminjam 2 buku lagi
        lib.borrowBook(m1.getMemberId(), "978-0261103573");
        lib.borrowBook(m1.getMemberId(), "978-0307269997"); // now Andi has 3 books (max)

        // Coba pinjam melebihi batas
        System.out.println("\n-- Andi mencoba meminjam lagi melebihi batas --");
        lib.borrowBook(m1.getMemberId(), "978-0201633610");

        // Perpanjang pinjaman pertama
        System.out.println("\n-- Perpanjangan pinjaman --");
        BorrowRecord rec = lib.getBorrowRecordsForMember(m1.getMemberId()).get(0);
        lib.extendLoan(rec.getRecordId(), 7); // extend 7 hari

        // Simulasikan keterlambatan: manipulasi tanggal (hanya untuk demo)
        System.out.println("\n-- Simulasi keterlambatan untuk satu record --");
        BorrowRecord lateRec = lib.getBorrowRecordsForMember(m1.getMemberId()).get(0);
        // Set borrowDate dan dueDate ke masa lalu untuk memicu denda
        lateRec.setBorrowDate(LocalDate.now().minusDays(40));
        lateRec.setDueDate(lateRec.getBorrowDate().plusDays(lib.getLoanPeriodDays()));
        System.out.println("Overdue? " + lateRec.isOverdue());
        System.out.println("Denda sekarang: " + lib.calculateFine(lateRec));

        // Pengembalian buku terlambat
        System.out.println("\n-- Pengembalian terlambat --");
        lib.returnBook(lateRec.getRecordId()); // akan hitung denda dan set buku tersedia

        // Laporan
        System.out.println("\n-- Laporan populer (top 3) --");
        lib.generatePopularBooksReport(3).forEach(b -> System.out.println(b.getTitle() + " (borrowCount=" + b.getBorrowCount() + ")"));

        System.out.println("\n-- Laporan member aktif --");
        lib.generateActiveMembersReport(10).forEach(m -> System.out.println(m.getName() + " | Joined: " + m.getJoinDate()));

        System.out.println("\n-- Statistik perpustakaan --");
        lib.displayLibraryStats();

        // Backup & maintenance
        System.out.println("\n-- Admin: backup & maintenance --");
        lib.backup();
        lib.maintenance();
    }
}

/* =========================
   CLASS: Book
   ========================= */
class Book {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int yearPublished;
    private BookCategory category;
    private boolean isAvailable;
    private int borrowCount;

    private static int totalBooks = 0;

    // Constructors
    public Book(String isbn, String title, String author, String publisher, int yearPublished, BookCategory category) {
        if (!LibraryUtils.isValidISBN(isbn)) throw new IllegalArgumentException("Invalid ISBN: " + isbn);
        this.isbn = isbn;
        this.title = title != null ? title : "Unknown";
        this.author = author != null ? author : "Unknown";
        this.publisher = publisher != null ? publisher : "Unknown";
        this.yearPublished = yearPublished;
        this.category = category == null ? BookCategory.OTHER : category;
        this.isAvailable = true;
        this.borrowCount = 0;
        totalBooks++;
    }

    // Getters / Setters (with validation where needed)
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public void setTitle(String title) { if (title != null && !title.isBlank()) this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { if (author != null && !author.isBlank()) this.author = author; }
    public String getPublisher() { return publisher; }
    public int getYearPublished() { return yearPublished; }
    public BookCategory getCategory() { return category; }
    public void setCategory(BookCategory category) { if (category != null) this.category = category; }

    public boolean isAvailable() { return isAvailable; }
    protected void setAvailable(boolean available) { this.isAvailable = available; } // package-private-ish for library control

    public int getBorrowCount() { return borrowCount; }
    protected void incrementBorrowCount() { borrowCount++; }

    public static int getTotalBooks() { return totalBooks; }

    // Business methods
    public boolean borrowBook() {
        if (!isAvailable) return false;
        setAvailable(false);
        incrementBorrowCount();
        return true;
    }

    public void returnBook() {
        setAvailable(true);
    }

    public double getPopularityScore() {
        int age = getAgeInYears();
        return borrowCount / Math.max(1.0, age); // simple heuristic
    }

    // Utility
    public void displayBookInfo() {
        System.out.println("[" + isbn + "] " + title + " - " + author + " (" + yearPublished + ") | Category: " + category + " | Available: " + isAvailable);
    }

    public boolean isClassic() {
        return getAgeInYears() >= 50;
    }

    public int getAgeInYears() {
        return (int) ChronoUnit.YEARS.between(LocalDate.of(yearPublished, 1, 1), LocalDate.now());
    }
}

/* =========================
   CLASS: Member
   ========================= */
class Member {
    private String memberId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDate joinDate;
    private MembershipType membershipType;
    private boolean isActive;

    private static int totalMembers = 0;
    public static final int MAX_BOOKS_ALLOWED = 3;

    public Member(String name, String email, String phone, String address, MembershipType membershipType) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (!LibraryUtils.isValidEmail(email)) throw new IllegalArgumentException("Invalid email");
        this.memberId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.joinDate = LocalDate.now();
        this.membershipType = membershipType == null ? MembershipType.PUBLIC : membershipType;
        this.isActive = true;
        totalMembers++;
    }

    // Getters/setters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public void setName(String name) { if (name != null && !name.isBlank()) this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { if (LibraryUtils.isValidEmail(email)) this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public LocalDate getJoinDate() { return joinDate; }
    public MembershipType getMembershipType() { return membershipType; }
    public void setMembershipType(MembershipType membershipType) { this.membershipType = membershipType; }
    public boolean isActive() { return isActive; }
    public void deactivate() { this.isActive = false; }

    // Business methods
    public boolean canBorrowMore(int currentBorrowedCount, int maxAllowed) {
        return isActive && currentBorrowedCount < maxAllowed;
    }

    public long calculateMembershipDuration() {
        return ChronoUnit.DAYS.between(joinDate, LocalDate.now());
    }

    public void upgradeMembership(MembershipType newType) {
        if (newType != null && newType != this.membershipType) this.membershipType = newType;
    }

    public static int getTotalMembers() { return totalMembers; }
}

/* =========================
   CLASS: BorrowRecord
   ========================= */
class BorrowRecord {
    private String recordId;
    private String memberId;
    private String isbn;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;

    private static int totalRecords = 0;

    public BorrowRecord(String memberId, String isbn, int loanPeriodDays) {
        this.recordId = UUID.randomUUID().toString();
        this.memberId = memberId;
        this.isbn = isbn;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(loanPeriodDays);
        this.returnDate = null;
        this.fine = 0.0;
        totalRecords++;
    }

    // Getters / setters (some package-private for Library control)
    public String getRecordId() { return recordId; }
    public String getMemberId() { return memberId; }
    public String getIsbn() { return isbn; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate date) { this.borrowDate = date; } // for simulation/testing only
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate d) { this.dueDate = d; } // for simulation/testing
    public LocalDate getReturnDate() { return returnDate; }
    public double getFine() { return fine; }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        this.fine = calculateFine(Library.FINE_PER_DAY);
    }

    public double calculateFine(double finePerDay) {
        LocalDate checkDate = (returnDate != null) ? returnDate : LocalDate.now();
        if (checkDate.isAfter(dueDate)) {
            long daysOver = ChronoUnit.DAYS.between(dueDate, checkDate);
            return daysOver * finePerDay;
        } else {
            return 0.0;
        }
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate) && returnDate == null;
    }

    public void extendLoan(int extraDays) {
        this.dueDate = this.dueDate.plusDays(extraDays);
    }

    public static int getTotalRecords() { return totalRecords; }
}

/* =========================
   CLASS: Library
   ========================= */
class Library {
    private List<Book> books;
    private List<Member> members;
    private List<BorrowRecord> borrowRecords;

    public static String libraryName = "Default Library";
    public static final double FINE_PER_DAY = 1.0; // currency unit per day

    private int maxBooksPerMember;
    private int loanPeriodDays;

    public Library(String libraryName, int maxBooksPerMember, int loanPeriodDays) {
        Library.libraryName = libraryName != null ? libraryName : Library.libraryName;
        this.maxBooksPerMember = maxBooksPerMember;
        this.loanPeriodDays = loanPeriodDays;
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.borrowRecords = new ArrayList<>();
    }

    // BOOK MANAGEMENT
    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
            System.out.println("Buku ditambahkan: " + book.getTitle());
        }
    }

    public void removeBook(String isbn) {
        books.removeIf(b -> b.getIsbn().equals(isbn));
    }

    public List<Book> searchBooks(String keyword) {
        String k = keyword == null ? "" : keyword.toLowerCase();
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(k) || b.getAuthor().toLowerCase().contains(k))
                .collect(Collectors.toList());
    }

    public List<Book> searchByCategory(BookCategory cat) {
        return books.stream().filter(b -> b.getCategory() == cat).collect(Collectors.toList());
    }

    public Book findAvailableByISBN(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn) && b.isAvailable()).findFirst().orElse(null);
    }

    public List<Book> getAvailableBooks() {
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    // MEMBER MANAGEMENT
    public Member registerMember(String name, String email, String phone, String address, MembershipType type) {
        try {
            Member m = new Member(name, email, phone, address, type);
            members.add(m);
            System.out.println("Member terdaftar: " + m.getName() + " (ID=" + m.getMemberId() + ")");
            return m;
        } catch (IllegalArgumentException ex) {
            System.out.println("Gagal registrasi member: " + ex.getMessage());
            return null;
        }
    }

    public void updateMember(String memberId, String name, String email) {
        Member m = getMemberById(memberId);
        if (m != null) {
            if (name != null && !name.isBlank()) m.setName(name);
            if (email != null && LibraryUtils.isValidEmail(email)) m.setEmail(email);
            System.out.println("Member updated: " + m.getName());
        } else {
            System.out.println("Member tidak ditemukan.");
        }
    }

    public Member getMemberById(String memberId) {
        return members.stream().filter(m -> m.getMemberId().equals(memberId)).findFirst().orElse(null);
    }

    public List<Member> getActiveMembers() {
        return members.stream().filter(Member::isActive).collect(Collectors.toList());
    }

    // BORROWING METHODS
    public boolean borrowBook(String memberId, String isbn) {
        Member m = getMemberById(memberId);
        Book b = books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);

        if (m == null) {
            System.out.println("Member tidak ditemukan.");
            return false;
        }
        if (b == null) {
            System.out.println("Buku tidak ditemukan.");
            return false;
        }
        int currentlyBorrowed = (int) borrowRecords.stream().filter(r -> r.getMemberId().equals(memberId) && r.getReturnDate() == null).count();
        if (!m.canBorrowMore(currentlyBorrowed, maxBooksPerMember)) {
            System.out.println("Tidak dapat meminjam: sudah mencapai batas atau status tidak aktif.");
            return false;
        }
        if (!b.isAvailable()) {
            System.out.println("Buku '" + b.getTitle() + "' tidak tersedia saat ini.");
            return false;
        }

        // Create record
        BorrowRecord record = new BorrowRecord(memberId, isbn, loanPeriodDays);
        borrowRecords.add(record);
        b.borrowBook();
        System.out.println("Peminjaman berhasil: " + b.getTitle() + " oleh " + m.getName() + ". Due: " + record.getDueDate());
        return true;
    }

    public boolean returnBook(String recordId) {
        BorrowRecord rec = borrowRecords.stream().filter(r -> r.getRecordId().equals(recordId)).findFirst().orElse(null);
        if (rec == null) {
            System.out.println("Record tidak ditemukan.");
            return false;
        }
        if (rec.getReturnDate() != null) {
            System.out.println("Buku sudah dikembalikan.");
            return false;
        }

        rec.returnBook(); // set return date and fine
        // set book available
        Book b = books.stream().filter(book -> book.getIsbn().equals(rec.getIsbn())).findFirst().orElse(null);
        if (b != null) b.returnBook();

        double fine = rec.getFine();
        System.out.println("Pengembalian tercatat. Fine: " + fine);
        return true;
    }

    public boolean extendLoan(String recordId, int extraDays) {
        BorrowRecord rec = borrowRecords.stream().filter(r -> r.getRecordId().equals(recordId)).findFirst().orElse(null);
        if (rec == null) {
            System.out.println("Record tidak ditemukan.");
            return false;
        }
        if (rec.getReturnDate() != null) {
            System.out.println("Tidak bisa perpanjang: sudah dikembalikan.");
            return false;
        }
        rec.extendLoan(extraDays);
        System.out.println("Perpanjangan berhasil. New due date: " + rec.getDueDate());
        return true;
    }

    public double calculateFine(BorrowRecord rec) {
        return rec.calculateFine(FINE_PER_DAY);
    }

    // REPORTING
    public List<Book> generatePopularBooksReport(int topN) {
        return books.stream()
                .sorted(Comparator.comparingInt(Book::getBorrowCount).reversed())
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<Member> generateActiveMembersReport(int topN) {
        // Sort by number of borrowed active records (descending)
        List<Member> active = getActiveMembers();
        return active.stream()
                .sorted(Comparator.comparingInt(m -> - (int) borrowRecords.stream().filter(r -> r.getMemberId().equals(m.getMemberId())).mapToInt(r -> (r.getReturnDate()==null?1:0)).sum()))
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<BorrowRecord> generateOverdueReport() {
        return borrowRecords.stream().filter(BorrowRecord::isOverdue).collect(Collectors.toList());
    }

    // Utility / admin
    public void displayLibraryStats() {
        System.out.println("Library: " + libraryName);
        System.out.println("Total books: " + Book.getTotalBooks());
        System.out.println("Total members: " + Member.getTotalMembers());
        System.out.println("Total borrow records: " + BorrowRecord.getTotalRecords());
        System.out.println("Currently borrowed: " + borrowRecords.stream().filter(r -> r.getReturnDate() == null).count());
    }

    public void backup() {
        System.out.println("[Backup] Data library dibackup (simulasi).");
    }

    public void maintenance() {
        System.out.println("[Maintenance] Optimizing library (simulasi).");
    }

    // Helpers for tests / demo
    public List<BorrowRecord> getBorrowRecordsForMember(String memberId) {
        return borrowRecords.stream().filter(r -> r.getMemberId().equals(memberId)).collect(Collectors.toList());
    }

    public int getLoanPeriodDays() { return loanPeriodDays; }
}

/* =========================
   CLASS: LibraryUtils (static utilities)
   ========================= */
class LibraryUtils {
    public static final int DEFAULT_LOAN_DAYS = 14;
    public static final double DEFAULT_FINE_PER_DAY = Library.FINE_PER_DAY;

    private LibraryUtils() { /* prevent instantiation */ }

    // Very basic ISBN check (structure check, not full validation)
    public static boolean isValidISBN(String isbn) {
        if (isbn == null) return false;
        String digits = isbn.replaceAll("[^0-9Xx]", "");
        return digits.length() >= 10 && digits.length() <= 13;
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.contains("@") && email.contains(".");
    }

    public static String formatDate(LocalDate d) {
        return d == null ? "N/A" : d.toString();
    }

    public static double calculateLateFee(long daysLate) {
        return daysLate * DEFAULT_FINE_PER_DAY;
    }
}

/* =========================
   ENUMS
   ========================= */
enum BookCategory {
    FICTION, NON_FICTION, SCIENCE, HISTORY, BIOGRAPHY, TECHNOLOGY, OTHER
}

enum MembershipType {
    STUDENT, FACULTY, PUBLIC
}