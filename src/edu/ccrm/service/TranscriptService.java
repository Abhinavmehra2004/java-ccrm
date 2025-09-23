package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;

import java.util.List;

public class TranscriptService {

    public String generateTranscript(Student student, List<Enrollment> enrollments) {
        StringBuilder transcript = new StringBuilder();
        transcript.append("Transcript for: ").append(student.getFullName()).append("\n");
        transcript.append("Registration Number: ").append(student.getRegNo()).append("\n");
        transcript.append("------------------------------------");

        for (Enrollment enrollment : enrollments) {
            transcript.append("Course: ").append(enrollment.getCourse().getTitle()).append("\n");
            transcript.append("Grade: ").append(enrollment.getGrade() != null ? enrollment.getGrade() : "Not Graded").append("\n");
            transcript.append("\n");
        }

        transcript.append("GPA: ").append(calculateGPA(enrollments)).append("\n");
        return transcript.toString();
    }

    public double calculateGPA(List<Enrollment> enrollments) {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getGrade() != null) {
                totalPoints += enrollment.getGrade().getGradePoint() * enrollment.getCourse().getCredits();
                totalCredits += enrollment.getCourse().getCredits();
            }
        }

        return totalCredits == 0 ? 0 : totalPoints / totalCredits;
    }
}
