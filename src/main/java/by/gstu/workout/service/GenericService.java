package by.gstu.workout.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GenericService<T> {
    T get(long id);
    List<T> getAll();
    T save(T obj);
    void delete(T object);
    List<T> getAll(Sort sort);
    Page<T> getAll(int pageNumber, int pageSize, String sortedField, Sort.Direction direction);
    Page<T> search(int pageNumber, int pageSize, String sortedField, Sort.Direction direction, String... searchObjects);
}
