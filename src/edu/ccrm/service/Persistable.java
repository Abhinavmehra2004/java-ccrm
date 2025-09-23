package edu.ccrm.service;

import java.io.IOException;
import java.util.List;

public interface Persistable<T> {
    void save(List<T> items) throws IOException;
    List<T> load() throws IOException;
}
