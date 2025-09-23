package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.exception.MaxCreditLimitExceededException;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.*;
import edu.ccrm.util.RecursiveFileUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final TranscriptService transcriptService = new TranscriptService();
    private final ImportExportService importExportService = new ImportExportService();
    private final BackupService backupService = new BackupService();

    public void displayMenu() {
        System.out.println("\nCampus Course & Records Manager (CCRM)");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollment & Grades");
        System.out.println("4. File Utilities");
        System.out.println("5. Reports");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    public void handleUserInput() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: manageStudents(); break;
                case 2: manageCourses(); break;
                case 3: manageEnrollment(); break;
                case 4: manageFileUtilities(); break;
                case 5: manageReports(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 0);
    }

    private void manageReports() {
        int choice;
        do {
            System.out.println("\n--- Reports ---");
            System.out.println("1. Find Top Students");
            System.out.println("2. Show Course Grade Distribution");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: findTopStudents(); break;
                case 2: showGradeDistribution(); break;
                case 0: break;
                default: System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    private void findTopStudents() {
        System.out.print("Enter minimum GPA for top students (e.g., 9.0): ");
        double minGpa = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\n--- Top Students (GPA > " + minGpa + ") ---");
        studentService.getAllStudents().stream()
            .filter(student -> transcriptService.calculateGPA(enrollmentService.getEnrollmentsForStudent(student)) > minGpa)
            .forEach(System.out::println);
    }

    private void showGradeDistribution() {
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();

        courseService.findCourseByCode(courseCode).ifPresent(course -> {
            System.out.println("\n--- Grade Distribution for " + course.getTitle() + " ---");
            enrollmentService.getAllEnrollments().stream()
                .filter(e -> e.getCourse().equals(course) && e.getGrade() != null)
                .collect(Collectors.groupingBy(Enrollment::getGrade, Collectors.counting()))
                .forEach((grade, count) -> System.out.println(grade + ": " + count));
        });
    }

    private void manageStudents() {
        int choice;
        do {
            System.out.println("\n--- Manage Students ---");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Update Student");
            System.out.println("4. Deactivate Student");
            System.out.println("5. View Student Profile");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: listStudents(); break;
                case 3: updateStudent(); break;
                case 4: deactivateStudent(); break;
                case 5: viewStudentProfile(); break;
                case 0: break;
                default: System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    private void addStudent() {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Registration Number: ");
        String regNo = scanner.nextLine();

        Student student = new Student(studentService.getAllStudents().size() + 1, new Name(firstName, lastName), email, LocalDate.now(), regNo);
        studentService.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private void listStudents() {
        System.out.println("\n--- List of Students ---");
        List<Student> students = studentService.getAllStudents();

        System.out.print("Sort by name? (y/n): ");
        String sortChoice = scanner.nextLine();
        if (sortChoice.equalsIgnoreCase("y")) {
            students.sort(new java.util.Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    return s1.getFullName().toString().compareTo(s2.getFullName().toString());
                }
            });
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void updateStudent() {
        System.out.print("Enter Registration Number of student to update: ");
        String regNo = scanner.nextLine();
        studentService.findStudentByRegNo(regNo).ifPresentOrElse(student -> {
            System.out.print("Enter new First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter new Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter new Email: ");
            String email = scanner.nextLine();

            student.setFullName(new Name(firstName, lastName));
            student.setEmail(email);
            System.out.println("Student updated successfully.");
        }, () -> System.out.println("Student not found."));
    }

    private void deactivateStudent() {
        System.out.print("Enter Registration Number of student to deactivate: ");
        String regNo = scanner.nextLine();
        studentService.findStudentByRegNo(regNo).ifPresentOrElse(student -> {
            student.setStatus(Student.StudentStatus.INACTIVE);
            System.out.println("Student deactivated successfully.");
        }, () -> System.out.println("Student not found."));
    }

    private void viewStudentProfile() {
        System.out.print("Enter Registration Number of student to view: ");
        String regNo = scanner.nextLine();
        studentService.findStudentByRegNo(regNo).ifPresentOrElse(
            student -> System.out.println(student.getProfile()),
            () -> System.out.println("Student not found.")
        );
    }

    private void manageCourses() {
        int choice;
        do {
            System.out.println("\n--- Manage Courses ---");
            System.out.println("1. Add Course");
            System.out.println("2. List Courses");
            System.out.println("3. Update Course");
            System.out.println("4. Deactivate Course");
            System.out.println("5. Search Courses");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addCourse(); break;
                case 2: listCourses(); break;
                case 3: updateCourse(); break;
                case 4: deactivateCourse(); break;
                case 5: searchCourses(); break;
                case 0: break;
                default: System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    private void addCourse() {
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();

        Course course = new Course.Builder(new CourseCode(code), title).credits(credits).build();
        courseService.addCourse(course);
        System.out.println("Course added successfully.");
    }

    private void listCourses() {
        System.out.println("\n--- List of Courses ---");
        courseService.getAllCourses().forEach(System.out::println);
    }

    private void updateCourse() {
        System.out.print("Enter Course Code of course to update: ");
        String code = scanner.nextLine();
        courseService.findCourseByCode(code).ifPresentOrElse(course -> {
            System.out.print("Enter new Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter new Credits: ");
            int credits = scanner.nextInt();
            scanner.nextLine();

            course.setTitle(title);
            course.setCredits(credits);
            System.out.println("Course updated successfully.");
        }, () -> System.out.println("Course not found."));
    }

    private void deactivateCourse() {
        System.out.print("Enter Course Code of course to deactivate: ");
        String code = scanner.nextLine();
        courseService.findCourseByCode(code).ifPresentOrElse(course -> {
            course.setStatus(Course.CourseStatus.INACTIVE);
            System.out.println("Course deactivated successfully.");
        }, () -> System.out.println("Course not found."));
    }

    private void searchCourses() {
        System.out.println("\n--- Search Courses ---");
        System.out.println("1. By Instructor");
        System.out.println("2. By Department");
        System.out.println("3. By Semester");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: 
                System.out.print("Enter instructor name: ");
                String instructorName = scanner.nextLine();
                List<Course> coursesByInstructor = courseService.search(courseService.getAllCourses(), c -> c.getInstructor() != null && c.getInstructor().getFullName().toString().equalsIgnoreCase(instructorName));
                coursesByInstructor.forEach(System.out::println);
                break;
            case 2: 
                System.out.print("Enter department: ");
                String department = scanner.nextLine();
                List<Course> coursesByDept = courseService.search(courseService.getAllCourses(), c -> c.getDepartment() != null && c.getDepartment().equalsIgnoreCase(department));
                coursesByDept.forEach(System.out::println);
                break;
            case 3: 
                System.out.print("Enter semester (SPRING, SUMMER, FALL, WINTER): ");
                String semesterStr = scanner.nextLine();
                try {
                    Semester semester = Semester.valueOf(semesterStr.toUpperCase());
                    List<Course> coursesBySem = courseService.search(courseService.getAllCourses(), c -> c.getSemester() == semester);
                    coursesBySem.forEach(System.out::println);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid semester.");
                }
                break;
            default: System.out.println("Invalid option.");
        }
    }

    private void manageEnrollment() {
        int choice;
        do {
            System.out.println("\n--- Manage Enrollment & Grades ---");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Unenroll Student from Course");
            System.out.println("3. Record Grade");
            System.out.println("4. Print Student Transcript");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: enrollStudent(); break;
                case 2: unenrollStudent(); break;
                case 3: recordGrade(); break;
                case 4: printTranscript(); break;
                case 0: break;
                default: System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    private void enrollStudent() {
        System.out.print("Enter Student Registration Number: ");
        String regNo = scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();

        studentService.findStudentByRegNo(regNo).ifPresent(student -> {
            courseService.findCourseByCode(courseCode).ifPresent(course -> {
                try {
                    enrollmentService.enrollStudent(student, course);
                    System.out.println("Enrollment successful.");
                } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            });
        });
    }

    private void unenrollStudent() {
        System.out.print("Enter Student Registration Number: ");
        String regNo = scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();

        studentService.findStudentByRegNo(regNo).ifPresent(student -> {
            courseService.findCourseByCode(courseCode).ifPresent(course -> {
                enrollmentService.unenrollStudent(student, course);
                System.out.println("Unenrollment successful.");
            });
        });
    }

    private void recordGrade() {
        System.out.print("Enter Student Registration Number: ");
        String regNo = scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter Grade (S, A, B, C, D, E, F): ");
        String gradeStr = scanner.nextLine();

        enrollmentService.getAllEnrollments().stream()
            .filter(e -> e.getStudent().getRegNo().equals(regNo) && e.getCourse().getCode().getCode().equals(courseCode))
            .findFirst()
            .ifPresent(enrollment -> {
                try {
                    Grade grade = Grade.valueOf(gradeStr.toUpperCase());
                    enrollment.setGrade(grade);
                    System.out.println("Grade recorded successfully.");
                } catch (IllegalArgumentException ex) {
                    System.out.println("Invalid grade.");
                }
            });
    }

    private void printTranscript() {
        System.out.print("Enter Student Registration Number: ");
        String regNo = scanner.nextLine();

        studentService.findStudentByRegNo(regNo).ifPresent(student -> {
            String transcript = transcriptService.generateTranscript(student, enrollmentService.getEnrollmentsForStudent(student));
            System.out.println(transcript);
        });
    }

    private void manageFileUtilities() {
        int choice;
        do {
            System.out.println("\n--- File Utilities ---");
            System.out.println("1. Import Students from CSV");
            System.out.println("2. Export Students to CSV");
            System.out.println("3. Import Courses from CSV");
            System.out.println("4. Export Courses to CSV");
            System.out.println("5. Create Backup");
            System.out.println("6. Show Backup Directory Size");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                String filePath;
                switch (choice) {
                    case 1: 
                        System.out.print("Enter path to students CSV file: ");
                        filePath = scanner.nextLine();
                        importExportService.importStudents(filePath, studentService); 
                        break;
                    case 2: 
                        System.out.print("Enter path to export students CSV file: ");
                        filePath = scanner.nextLine();
                        importExportService.exportStudents(filePath, studentService); 
                        break;
                    case 3: 
                        System.out.print("Enter path to courses CSV file: ");
                        filePath = scanner.nextLine();
                        importExportService.importCourses(filePath, courseService); 
                        break;
                    case 4: 
                        System.out.print("Enter path to export courses CSV file: ");
                        filePath = scanner.nextLine();
                        importExportService.exportCourses(filePath, courseService); 
                        break;
                    case 5: backupService.backupData(); System.out.println("Backup created."); break;
                    case 6: 
                        long size = RecursiveFileUtils.getDirectorySize(Paths.get(AppConfig.getInstance().getProperty("backup.folder")));
                        System.out.println("Backup directory size: " + size + " bytes");
                        break;
                    case 0: break;
                    default: System.out.println("Invalid option.");
                }
            } catch (IOException e) {
                System.out.println("An file I/O error occurred: " + e.getMessage());
            }
        } while (choice != 0);
    }
}