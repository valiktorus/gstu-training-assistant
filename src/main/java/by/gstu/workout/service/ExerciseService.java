package by.gstu.workout.service;

import by.gstu.workout.model.Exercise;
import by.gstu.workout.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService implements GenericService<Exercise> {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Exercise get(long id) {
        return exerciseRepository.findById(id).orElseThrow(()-> new RuntimeException("no such exercise"));
    }

    @Override
    public List<Exercise> getAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise save(Exercise obj) {
        return exerciseRepository.save(obj);
    }

    @Override
    public void delete(Exercise object) {
        exerciseRepository.delete(object);
    }

    @Override
    public List<Exercise> getAll(Sort sort) {
        return exerciseRepository.findAll(sort);
    }

    @Override
    public Page<Exercise> getAll(int pageNumber, int pageSize, String sortedField, Sort.Direction direction) {
        return exerciseRepository.findAll(PageRequest.of(pageNumber, pageSize, direction, sortedField));
    }

    @Override
    public Page<Exercise> search(int pageNumber, int pageSize, String sortedField, Sort.Direction direction, String... searchObjects) {
        return exerciseRepository.findAll(PageRequest.of(pageNumber, pageSize, direction, searchObjects));
    }

    public Page<Exercise> getAll(int pageNumber, int pageSize){
        return exerciseRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
}
