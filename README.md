# Campus Course & Records Manager (CCRM)

## 1. Project Overview

A console-based Java application to manage students, courses, enrollments, and grades for an educational institute, built as per the project statement requirements. The application demonstrates a wide range of Java SE features, from core language constructs to advanced topics like NIO.2, Design Patterns, and the Streams API.

### How to Run

- **JDK Version:** 17 or higher
- **Compile:** From the `ccrm` root directory, run:
  ```bash
  javac -d out $(find . -name "*.java")
  ```
- **Run:**
  ```bash
  java -cp out edu.ccrm.cli.Main
  ```

---

## 2. Java Platform & Architecture

### Evolution of Java

- **JDK 1.0 (1996):** The initial release of Java.
- **J2SE 1.4 (2002):** Introduced assertions, regular expressions, and chained exceptions.
- **Java SE 5 (2004):** A major release that introduced generics, annotations, enums, varargs, and the `java.util.concurrent` package.
- **Java SE 8 (2014):** A landmark release featuring Lambdas, the Streams API, a new Date/Time API (java.time), and default methods in interfaces.
- **Java SE 11 (2018):** The first Long-Term Support (LTS) release under the new six-month release cadence.
- **Java SE 17 (2021):** The latest LTS release, bringing features like Sealed Classes and Pattern Matching for `instanceof`.

### Java ME vs SE vs EE

| Feature      | Java ME (Micro Edition)                               | Java SE (Standard Edition)                               | Java EE (Enterprise Edition) / Jakarta EE                 |
|--------------|-------------------------------------------------------|----------------------------------------------------------|-----------------------------------------------------------|
| **Target**   | Mobile, embedded systems, and other small devices.    | General-purpose computing on desktops and servers.       | Large-scale, distributed, and transactional applications. |
| **APIs**     | A subset of Java SE APIs, plus specific libraries for mobile devices. | The core Java APIs (collections, I/O, networking, etc.). | Extends Java SE with APIs for web services, servlets, messaging, etc. |
| **Example**  | Old feature phone applications.                       | This CCRM project, Minecraft (Java Edition).             | Large banking systems, e-commerce backends.               |

### JDK, JRE, and JVM

- **JVM (Java Virtual Machine):** The heart of the Java platform. It's an abstract machine that provides a runtime environment in which Java bytecode can be executed. It is what makes Java platform-independent ("write once, run anywhere").
- **JRE (Java Runtime Environment):** A software package that contains everything needed to *run* a compiled Java application. It includes the JVM, the Java Class Library, and other supporting files.
- **JDK (Java Development Kit):** A superset of the JRE. It contains everything in the JRE, plus the tools necessary to *develop* Java applications, most notably the `javac` compiler.

![Flowchart: JDK contains JRE, which contains JVM]

---

## 3. Setup and Installation (Screenshots)

*(This section is a placeholder for you to add your own screenshots as required.)*

### Windows Installation & Verification

1.  Download the JDK from the Oracle website or a vendor like Adoptium.
2.  Run the installer.
3.  Set the `JAVA_HOME` environment variable.
4.  Add the JDK's `bin` directory to the `PATH` environment variable.

**Verification (`java -version`):**

`[YOUR SCREENSHOT HERE]`

### Eclipse IDE Setup

1.  Create a new Java Project.
2.  Create packages as per the project structure (`edu.ccrm.cli`, `edu.ccrm.domain`, etc.).
3.  Create a run configuration for the `edu.ccrm.cli.Main` class.

**Eclipse Project and Run Configuration:**

`[YOUR SCREENSHOTS HERE]`

---

## 4. Syllabus Topic to Code Mapping Table

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

## 5. Usage and Sample Data

### Sample Commands

-   **Import Data:** Navigate to `4. File Utilities` -> `1. Import Students from CSV` and enter `test-data/students.csv`.
-   **List Students:** Navigate to `1. Manage Students` -> `2. List Students`.
-   **Enroll:** Navigate to `3. Manage Enrollment & Grades` -> `1. Enroll Student in Course`.

### Sample Data Files

The `test-data` directory contains `students.csv` and `courses.csv` with sample data to import.

### Enabling Assertions

To run the application with assertions enabled (a technical requirement), use the `-ea` flag:

```bash
java -ea -cp out edu.ccrm.cli.Main
```