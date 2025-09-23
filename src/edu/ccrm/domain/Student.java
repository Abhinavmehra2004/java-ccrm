package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    private StudentStatus status;
    private List<Course> enrolledCourses;

    public enum StudentStatus {
        ACTIVE, INACTIVE, GRADUATED
    }

    public Student(int id, Name fullName, String email, LocalDate dateOfBirth, String regNo) {
        super(id, fullName, email, dateOfBirth);
        this.regNo = regNo;
        this.status = StudentStatus.ACTIVE;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public List<Course> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    public void enrollCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
    }

    public void unenrollCourse(Course course) {
        enrolledCourses.remove(course);
    }

    @Override
    public String getProfile() {
        return new StringBuilder("Student Profile:\n")
                .append("ID: ").append(getId()).append("\n")
                .append("Registration No: ").append(regNo).append("\n")
                .append("Full Name: ").append(getFullName()).append("\n")
                .append("Email: ").append(getEmail()).append("\n")
                .append("Status: ").append(status).append("\n")
                .append("Date of Birth: ").append(getDateOfBirth())
                .toString();
    }

    @Override
    public String toString() {
        return "Student [regNo=" + regNo + ", " + super.toString() + "]";
    }
}
