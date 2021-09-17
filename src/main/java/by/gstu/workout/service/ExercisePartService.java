package by.gstu.workout.service;

import by.gstu.workout.model.ExercisePart;
import by.gstu.workout.repository.CustomNativeRepositoryImpl;
import by.gstu.workout.repository.ExercisePartRepository;
import by.gstu.workout.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercisePartService {
    @Autowired
    private ExercisePartRepository exercisePartRepository;
    @Autowired
    private CustomNativeRepositoryImpl customNativeRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    public ExercisePart get(long id) {
        return exercisePartRepository.findById(id).orElseThrow(()-> new RuntimeException("no such exercise"));
    }
    public List<ExercisePart> getAll(){
        return exercisePartRepository.findAll();
    }

    public void insert(ExercisePart exercisePart) {
        customNativeRepository.insertExercisePart(exercisePart);
    }
    public void save(ExercisePart exercisePart) {
        exercisePartRepository.save(exercisePart);
    }
    public void delete(ExercisePart exercisePart){
        exercisePartRepository.delete(exercisePart);
    }
}
