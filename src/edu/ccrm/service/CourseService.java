package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CourseService implements Searchable<Course> {
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> findCourseByCode(String code) {
        return courses.stream()
                .filter(c -> c.getCode().getCode().equals(code))
                .findFirst();
    }

    @Override
    public List<Course> search(List<Course> items, Predicate<Course> predicate) {
        return items.stream()
                .filter(predicate)
                .toList();
    }
}
