package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class StudentService implements Searchable<Student> {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Optional<Student> findStudentByRegNo(String regNo) {
        return students.stream()
                .filter(s -> s.getRegNo().equals(regNo))
                .findFirst();
    }

    public void updateStudent(Student updatedStudent) {
        findStudentByRegNo(updatedStudent.getRegNo()).ifPresent(student -> {
            int index = students.indexOf(student);
            students.set(index, updatedStudent);
        });
    }

    @Override
    public List<Student> search(List<Student> items, Predicate<Student> predicate) {
        return items.stream()
                .filter(predicate)
                .toList();
    }
}
