package edu.ccrm.service;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.exception.MaxCreditLimitExceededException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private List<Enrollment> enrollments = new ArrayList<>();
    private final int maxCredits = AppConfig.getInstance().getIntProperty("max.credits");

    public void enrollStudent(Student student, Course course) throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        if (isEnrolled(student, course)) {
            throw new DuplicateEnrollmentException("Student is already enrolled in this course.");
        }

        int currentCredits = student.getEnrolledCourses().stream().mapToInt(Course::getCredits).sum();
        if (currentCredits + course.getCredits() > maxCredits) {
            throw new MaxCreditLimitExceededException("Enrolling in this course exceeds the maximum credit limit.");
        }

        student.enrollCourse(course);
        enrollments.add(new Enrollment(student, course, LocalDate.now()));
    }

    public void unenrollStudent(Student student, Course course) {
        student.unenrollCourse(course);
        enrollments.removeIf(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
    }

    public boolean isEnrolled(Student student, Course course) {
        return student.getEnrolledCourses().contains(course);
    }

    public List<Enrollment> getEnrollmentsForStudent(Student student) {
        return enrollments.stream()
                .filter(e -> e.getStudent().equals(student))
                .toList();
    }
    
    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }
}
