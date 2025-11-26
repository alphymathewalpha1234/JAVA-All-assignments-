import java.io.*;
import java.util.*;


class Book implements Serializable, Comparable<Book> {
    private Integer bookId;
    private String title;
    private String author;
    private String category;
    private boolean isIssued;

    public Book(Integer bookId, String title, String author, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = false;
    }

    public Integer getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean getIsIssued() { return isIssued; }

    public void markAsIssued() { this.isIssued = true; }
    public void markAsReturned() { this.isIssued = false; }

    public void displayBookDetails() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Category: " + category);
        System.out.println("Issued: " + (isIssued ? "Yes" : "No"));
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title); 
    }
}


class Member implements Serializable {
    private Integer memberId;
    private String name;
    private String email;
    private List<Integer> issuedBooks;

    public Member(Integer memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.issuedBooks = new ArrayList<>();
    }

    public Integer getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void addIssuedBook(int bookId) {
        issuedBooks.add(bookId);
    }

    public void returnIssuedBook(int bookId) {
        issuedBooks.remove(Integer.valueOf(bookId));
    }

    public void displayMemberDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Issued Books: " + issuedBooks);
    }
}


class LibraryManager {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();
    private static final String BOOK_FILE = "books.txt";
    private static final String MEMBER_FILE = "members.txt";

    public LibraryManager() {
        loadFromFile();
    }

    
    public void addBook(Scanner sc) {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        Book book = new Book(id, title, author, category);
        books.put(id, book);
        System.out.println("Book added successfully!");
        saveToFile();
    }

    
    public void addMember(Scanner sc) {
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        Member member = new Member(id, name, email);
        members.put(id, member);
        System.out.println("Member added successfully!");
        saveToFile();
    }

    
    public void issueBook(Scanner sc) {
        System.out.print("Enter Book ID to issue: ");
        int bookId = sc.nextInt();
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();

        Book book = books.get(bookId);
        Member member = members.get(memberId);

        if (book == null || member == null) {
            System.out.println("Invalid Book or Member ID.");
            return;
        }

        if (book.getIsIssued()) {
            System.out.println("Book already issued!");
        } else {
            book.markAsIssued();
            member.addIssuedBook(bookId);
            System.out.println("Book issued successfully!");
        }
        saveToFile();
    }

    
    public void returnBook(Scanner sc) {
        System.out.print("Enter Book ID to return: ");
        int bookId = sc.nextInt();
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();

        Book book = books.get(bookId);
        Member member = members.get(memberId);

        if (book == null || member == null) {
            System.out.println("Invalid Book or Member ID.");
            return;
        }

        if (!book.getIsIssued()) {
            System.out.println("Book was not issued.");
        } else {
            book.markAsReturned();
            member.returnIssuedBook(bookId);
            System.out.println("Book returned successfully!");
        }
        saveToFile();
    }

    
    public void searchBooks(Scanner sc) {
        System.out.print("Enter keyword (title/author/category): ");
        sc.nextLine();
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(keyword)
                    || b.getAuthor().toLowerCase().contains(keyword)
                    || b.getCategory().toLowerCase().contains(keyword)) {
                b.displayBookDetails();
                System.out.println("-------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No books found!");
    }

    
    public void sortBooks() {
        List<Book> sortedBooks = new ArrayList<>(books.values());
        Collections.sort(sortedBooks);
        System.out.println("Books sorted by title:");
        for (Book b : sortedBooks) {
            b.displayBookDetails();
            System.out.println("-------------------");
        }
    }

    
    private void saveToFile() {
        try (ObjectOutputStream bos = new ObjectOutputStream(new FileOutputStream(BOOK_FILE));
             ObjectOutputStream mos = new ObjectOutputStream(new FileOutputStream(MEMBER_FILE))) {
            bos.writeObject(books);
            mos.writeObject(members);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
private void loadFromFile() {
    try (ObjectInputStream bis = new ObjectInputStream(new FileInputStream(BOOK_FILE));
         ObjectInputStream mis = new ObjectInputStream(new FileInputStream(MEMBER_FILE))) {

        books = (Map<Integer, Book>) bis.readObject();
        members = (Map<Integer, Member>) mis.readObject();

    } catch (Exception e) {
        System.out.println("Starting fresh library records...");
    }
}

}


public class CityLibrary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryManager lib = new LibraryManager();

        while (true) {
            System.out.println("\n===== City Library Digital Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1: lib.addBook(sc); break;
                case 2: lib.addMember(sc); break;
                case 3: lib.issueBook(sc); break;
                case 4: lib.returnBook(sc); break;
                case 5: lib.searchBooks(sc); break;
                case 6: lib.sortBooks(); break;
                case 7:
                    System.out.println("Exiting... Data saved successfully.");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
