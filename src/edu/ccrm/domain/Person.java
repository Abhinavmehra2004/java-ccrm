package edu.ccrm.domain;

import java.time.LocalDate;

public abstract class Person {
    private int id;
    private Name fullName;
    private String email;
    private LocalDate dateOfBirth;

    public Person(int id, Name fullName, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Name getFullName() {
        return fullName;
    }

    public void setFullName(Name fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", fullName=" + fullName + ", email=" + email + ", dateOfBirth=" + dateOfBirth + "]";
    }

    public abstract String getProfile();
}
