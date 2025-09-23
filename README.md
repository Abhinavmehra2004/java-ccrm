# Campus Course & Records Manager (CCRM)

Welcome to CCRM! This project is a comprehensive, console-based tool built with Java SE for managing students, courses, and academic records at an educational institute. It's a demonstration of modern Java development principles, showcasing everything from object-oriented design to advanced file I/O with NIO.2.

---

## Getting Started

Ready to run the application? Here’s how.

### Prerequisites

- **Java Development Kit (JDK):** Version 17 or higher.

### Installation & Execution

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Abhinavmehra2004/java-ccrm.git
    cd java-ccrm
    ```

2.  **Compile the code:** From the project's root directory, run the following command. This will compile all Java source files and place the `.class` files into the `out` directory.
    ```bash
    javac -d out $(find . -name "*.java")
    ```

3.  **Run the application:**
    ```bash
    java -cp out edu.ccrm.cli.Main
    ```

The CCRM menu will appear, and you can start exploring the features!

---

## Screenshots

### Setup & Installation

**JDK Installation Verification (`java -version`):**

*Delete the incorrect screenshot below*
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.35.09.png)
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.41.11.png)

**Eclipse IDE Project Setup & Run Configuration:**

*Delete the incorrect screenshot below*
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.35.09.png)
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.41.11.png)

### Program in Action

*(As I cannot see the content of the images, they are all listed here. Please feel free to edit the README to move them under more specific headings!)*

**Main Menu:**
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2016.59.49.png)

**Student Management:**
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.01.02.png)

**Course Management:**
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.03.05.png)

**File Utilities:**
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.04.06.png)

**Importing Data:**
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.04.20.png)

**Listing Imported Data:**
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.04.32.png)

**Reports Menu:**
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.05.36.png)

**Backup Folder:**
![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.06.21.png)

---

## About the Technology: A Java Deep Dive

This project was built entirely with Java SE. Here’s a quick look at the platform and concepts that power CCRM.

### The Java Platform at a Glance

-   **JVM (Java Virtual Machine):** The engine that runs the compiled Java code. It’s the magic that makes Java a "write once, run anywhere" language.
-   **JRE (Java Runtime Environment):** The full package needed to *run* Java applications. It bundles the JVM and the core Java libraries.
-   **JDK (Java Development Kit):** The complete toolkit for *building* Java applications. It includes the JRE and developer tools like the `javac` compiler.

### Java Editions: SE, EE, and ME

Java comes in different flavors for different needs. This project uses **Java SE (Standard Edition)**, the foundation for desktop and server applications. The other main editions are **Java EE (Enterprise Edition)** for large-scale business applications and **Java ME (Micro Edition)** for small and embedded devices.

---

## Code Tour: Where to Find Key Concepts

This project was built to demonstrate a wide range of Java features. This table serves as a map to help you find where specific concepts are implemented in the source code.

| Syllabus Topic | File / Class / Method Where Demonstrated |
|----------------|------------------------------------------|
| **Packages** | The entire project is organized into packages like `edu.ccrm.domain`, `edu.ccrm.service`, etc. |
| **Loops (do-while, for-each)** | `Menu.java` (all `manage...` methods use `do-while`), `listStudents()` uses an enhanced-for loop. |
| **Decision Structures (if, switch)** | `Menu.java` (all `handleUserInput` and `manage...` methods use `switch`), `ImportExportService.java` uses `if`. |
| **Arrays & `String.split`** | `ImportExportService.java` uses `line.split(",")` to parse CSV files into a `String[]`. |
| **Encapsulation** | All domain classes (`Student`, `Course`) have `private` fields with `public` getters/setters. |
| **Inheritance (`extends`, `super`)** | `Student.java` and `Instructor.java` extend the abstract `Person.java` class, using `super()` in their constructors. |
| **Abstraction (abstract class/method)** | `Person.java` is an abstract class with an abstract `getProfile()` method. |
| **Polymorphism** | `Student` and `Instructor` objects can be treated as `Person` types. `getProfile()` is a polymorphic method call. |
| **Immutability (`final` class/fields)** | `Name.java` and `CourseCode.java` are immutable value classes with `final` fields. |
| **Static Nested Class** | `Course.Builder` is a static nested class inside `Course.java`. |
| **Anonymous Inner Class** | `Menu.java` -> `listStudents()` uses an anonymous `java.util.Comparator` to sort students. |
| **Interfaces** | `Persistable.java` and `Searchable.java` are interfaces implemented by services. |
| **Enums with Constructors & Fields** | `Grade.java` is an enum with a `gradePoint` field and a constructor. |
| **Functional Interfaces & Lambdas** | `Menu.java` -> `searchCourses()` uses lambda expressions (`c -> ...`) as predicates for filtering. |
| **Design Pattern: Singleton** | `AppConfig.java` is implemented as a Singleton. |
| **Design Pattern: Builder** | `Course.java` uses a static nested `Builder` class for object construction. |
| **Custom Exceptions** | `DuplicateEnrollmentException.java`, `MaxCreditLimitExceededException.java`. |
| **Exception Handling (try-catch)** | `Menu.java` -> `enrollStudent()` uses a `try-catch` block. `manageFileUtilities` also uses `try-catch`. |
| **File I/O (NIO.2)** | `ImportExportService.java` and `BackupService.java` use `java.nio.file.Path`, `Files`, and `Paths`. |
| **Streams API (`filter`, `map`, `collect`)** | `Menu.java` -> `showGradeDistribution()` uses a stream pipeline to group and count grades. `searchCourses()` uses `filter`. |
| **Date/Time API** | `Enrollment.java` uses `LocalDate`. `BackupService.java` uses `LocalDateTime` for timestamps. |
| **Recursion** | `RecursiveFileUtils.java` -> `getDirectorySize()` uses `Files.walkFileTree` which is a form of recursion over a directory structure. |

---

## Usage Notes

### Importing Data

The `test-data` directory contains sample CSV files. To import them:
1.  Navigate to `4. File Utilities` in the main menu.
2.  Select `1. Import Students from CSV`.
3.  When prompted, enter the path: `test-data/students.csv`.
4.  Repeat for courses with `test-data/courses.csv`.

### Enabling Assertions

To run the application with assertions enabled (a technical requirement), use the `-ea` flag:

```bash
java -ea -cp out edu.ccrm.cli.Main
```