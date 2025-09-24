# Campus Course & Records Manager (CCRM)

Welcome to CCRM! This is a complete, console-based Java SE application that manages the academic records, courses, and students of an educational institution. It's an example of leading-edge Java development practices, demonstrating everything from object-oriented design through to sophisticated file I/O using NIO.2.

---

## Getting Started

Ready to run the application? Here's how.

### Prerequisites

- Java Development Kit (JDK): Version 17 or later.

### Installation & Execution

1.  Clone the repository:
    ```bash
    git clone https://github.com/Abhinavmehra2004/java-ccrm.git
    cd java-ccrm
    ```

2.  Compile the code: Execute this command at the root folder of the project. This will compile all Java .java files and put the compiled .class files into the out folder.
    ```bash
    javac -d out $(find . -name "*.java")
    ```

3.  Run the application:
    ```bash
    java -cp out edu.ccrm.cli.Main
    ```

The CCRM menu will show up, and you can begin to explore the features!

---

## Screenshots

### Setup & Installation

**JDK Installation Verification (java -version):**

![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.41.11.png)



**Eclipse IDE Project Setup & Run Configuration:**


![Screenshot](screenshots/Screenshot%202025-09-23%20at%2017.35.09.png)


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

Java SE was used to create the entire project. Below is a quick overview of the platform and concepts that motivate CCRM.

### Java Platform Overview

-   JVM (Java Virtual Machine): The run-time environment for the compiled Java code. It's the magic that turns Java into a "write once, run anywhere" technology.
-   JRE (Java Runtime Environment): The whole package required to execute Java applications. It includes the JVM and the standard collection of Java libraries.
-   JDK (Java Development Kit): The whole kit used to construct Java programs. It includes the JRE and development tools such as the javac compiler.

### Java Editions: SE, EE, and ME

Java comes in different flavors according to needs.The other well-known ones are Java EE (Enterprise Edition) for business applications of large scale and Java ME (Micro Edition) for small devices and embedded devices.

---

## Code Tour: Where to Find Key Concepts

This project was built for the purpose of demonstrating a wide range of features of Java. This table serves as a navigation guide to direct you to where specific concepts are implemented in the source code.

| Syllabus Topic | File / Class / Method Where Demonstrated |
|---|---|
| Packages | The project is broken into packages such as edu.ccrm.domain, edu.ccrm.service, etc. |
| Loops (do-while, for-each) | Menu.java (all manage... methods employ do-while), listStudents() employs an enhanced-for loop. |
| Decision Structures (if, switch) | Menu.java (all handleUserInput and manage... methods employ switch), ImportExportService.java employs if. |
| Arrays & String.split | ImportExportService.java employs line.split(",") to split CSV files into a String[]. |
| Encapsulation | All domain classes (Student, Course) encapsulate data with private data and public getters/setters. |
| Inheritance (extends, super) | Student.java and Instructor.java extend the abstract Person.java class using super() in their constructors. |
| Abstraction (abstract class/method) | Person.java is an abstract class with an abstract getProfile() method. |
| Polymorphism | Student and Instructor objects are of type Person. getProfile() is a polymorphic call. |
| Immutability (final class/fields) | Name.java and CourseCode.java are final field value classes. |
| Static Nested Class | Course.Builder is a static nested class in Course.java. |
| Anonymous Inner Class | Menu.java -> listStudents() uses an anonymous java.util.Comparator to sort students. |
| Interfaces | Persistable.java and Searchable.java are interfaces that services implement. |
| Enums with Constructors & Fields | Grade.java is an enum with a gradePoint field and a constructor. |
| Functional Interfaces & Lambdas | Menu.java -> searchCourses() employs lambda expressions (c -> ...) to filter as predicates. |
| Design Pattern: Singleton | AppConfig.java is designed as a Singleton. |
| Design Pattern: Builder | Course.java has a static nested Builder class for object construction. |
| Custom Exceptions | DuplicateEnrollmentException.java, MaxCreditLimitExceededException.java. |
| Exception Handling (try-catch) | Menu.java -> enrollStudent() employs a try-catch block. manageFileUtilities also employs try-catch. |
| File I/O (NIO.2) | ImportExportService.java and BackupService.java utilize java.nio.file.Path, Files, and Paths. |
| Streams API (filter, map, collect) | Menu.java -> showGradeDistribution() employs a stream pipeline to add up and count grades. searchCourses() employs filter. |
| Date/Time API | Enrollment.java utilizes LocalDate. BackupService.java utilizes LocalDateTime for timestamps. |
| Recursion | RecursiveFileUtils.java -> getDirectorySize() employs Files.walkFileTree which is an example of recursion over a tree of files. |

---

## Usage Notes

### Importing Data

dummy CSV files are present in the test-data directory. To import them:

1.  Go to 4. File Utilities in the main menu.
2.  Choose 1. Import Students from CSV.
3.  When asked, type in: test-data/students.csv.
4.  Repeat the same for courses with test-data/courses.csv.

### Enabling Assertions

To run the application with assertions enabled (a technical requirement), use the flag -ea:

```bash
java -ea -cp out edu.ccrm.cli.Main
```
