package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private String department;
    private List<Course> assignedCourses;

    public Instructor(int id, Name fullName, String email, LocalDate dateOfBirth, String department) {
        super(id, fullName, email, dateOfBirth);
        this.department = department;
        this.assignedCourses = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Course> getAssignedCourses() {
        return new ArrayList<>(assignedCourses);
    }

    public void assignCourse(Course course) {
        if (!assignedCourses.contains(course)) {
            assignedCourses.add(course);
            course.setInstructor(this);
        }
    }

    public void unassignCourse(Course course) {
        if (assignedCourses.remove(course)) {
            course.setInstructor(null);
        }
    }

    @Override
    public String getProfile() {
        return new StringBuilder("Instructor Profile:\n")
                .append("ID: ").append(getId()).append("\n")
                .append("Full Name: ").append(getFullName()).append("\n")
                .append("Email: ").append(getEmail()).append("\n")
                .append("Department: ").append(department).append("\n")
                .append("Date of Birth: ").append(getDateOfBirth())
                .toString();
    }

    @Override
    public String toString() {
        return "Instructor [department=" + department + ", " + super.toString() + "]";
    }
}
