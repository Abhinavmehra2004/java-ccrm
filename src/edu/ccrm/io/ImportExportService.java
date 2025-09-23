package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.CourseCode;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Name;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ImportExportService {

    public void importStudents(String filePath, StudentService studentService) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            System.out.println("Student data file not found: " + filePath);
            return;
        }

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                Student student = new Student(
                    Integer.parseInt(parts[0]),
                    new Name(parts[1], parts[2]),
                    parts[3],
                    LocalDate.parse(parts[4]),
                    parts[5]
                );
                studentService.addStudent(student);
            }
        }
        System.out.println("Students imported successfully.");
    }

    public void exportStudents(String filePath, StudentService studentService) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = studentService.getAllStudents().stream()
                .map(s -> String.join(",", 
                    String.valueOf(s.getId()), 
                    s.getFullName().getFirstName(), 
                    s.getFullName().getLastName(),
                    s.getEmail(),
                    s.getDateOfBirth().toString(),
                    s.getRegNo()
                ))
                .collect(Collectors.toList());
        Files.write(path, lines);
        System.out.println("Students exported successfully.");
    }

    public void importCourses(String filePath, CourseService courseService) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            System.out.println("Course data file not found: " + filePath);
            return;
        }

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                // For simplicity, creating a new instructor on the fly.
                // A more robust implementation would look up existing instructors.
                Instructor instructor = new Instructor(0, new Name(parts[3], ""), "", LocalDate.now(), parts[5]);
                Course course = new Course.Builder(new CourseCode(parts[0]), parts[1])
                    .credits(Integer.parseInt(parts[2]))
                    .instructor(instructor)
                    .semester(Semester.valueOf(parts[4]))
                    .department(parts[5])
                    .build();
                courseService.addCourse(course);
            }
        }
        System.out.println("Courses imported successfully.");
    }

    public void exportCourses(String filePath, CourseService courseService) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = courseService.getAllCourses().stream()
                .map(c -> String.join(",", 
                    c.getCode().getCode(),
                    c.getTitle(),
                    String.valueOf(c.getCredits()),
                    c.getInstructor() != null ? c.getInstructor().getFullName().toString() : "",
                    c.getSemester() != null ? c.getSemester().toString() : "",
                    c.getDepartment() != null ? c.getDepartment() : ""
                ))
                .collect(Collectors.toList());
        Files.write(path, lines);
        System.out.println("Courses exported successfully.");
    }

    public void exportEnrollments(String filePath, EnrollmentService enrollmentService) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = enrollmentService.getAllEnrollments().stream()
                .map(e -> String.join(",",
                    e.getStudent().getRegNo(),
                    e.getCourse().getCode().getCode(),
                    e.getEnrollmentDate().toString(),
                    e.getGrade() != null ? e.getGrade().toString() : ""
                ))
                .collect(Collectors.toList());
        Files.write(path, lines);
        System.out.println("Enrollments exported successfully.");
    }
}
