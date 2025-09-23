package edu.ccrm.domain;

public class Course {
    private CourseCode code;
    private String title;
    private int credits;
    private Instructor instructor;
    private Semester semester;
    private String department;
    private CourseStatus status;

    public enum CourseStatus { ACTIVE, INACTIVE }

    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
        this.department = builder.department;
        this.status = CourseStatus.ACTIVE;
    }

    // Getters and Setters
    public CourseCode getCode() { return code; }
    public void setCode(CourseCode code) { this.code = code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
    public Semester getSemester() { return semester; }
    public void setSemester(Semester semester) { this.semester = semester; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public CourseStatus getStatus() { return status; }
    public void setStatus(CourseStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Course [code=" + code + ", title=" + title + ", credits=" + credits +
                ", instructor=" + (instructor != null ? instructor.getFullName() : "N/A") +
                ", semester=" + semester + ", department=" + department + ", status=" + status + "]";
    }

    public static class Builder {
        private CourseCode code;
        private String title;
        private int credits;
        private Instructor instructor;
        private Semester semester;
        private String department;

        public Builder(CourseCode code, String title) {
            this.code = code;
            this.title = title;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this;
        }

        public Builder instructor(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder semester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}
