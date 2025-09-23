package edu.ccrm.service;

import java.util.List;
import java.util.function.Predicate;

public interface Searchable<T> {
    List<T> search(List<T> items, Predicate<T> predicate);
}
