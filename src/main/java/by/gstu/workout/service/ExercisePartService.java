package by.gstu.workout.service;

import by.gstu.workout.model.ExercisePart;
import by.gstu.workout.repository.CustomNativeRepositoryImpl;
import by.gstu.workout.repository.ExercisePartRepository;
import by.gstu.workout.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Exercise part service.
 */
@Service
public class ExercisePartService {
    @Autowired
    private ExercisePartRepository exercisePartRepository;
    @Autowired
    private CustomNativeRepositoryImpl customNativeRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    /**
     * Get exercise part.
     *
     * @param id the id
     * @return the exercise part
     */
    public ExercisePart get(long id) {
        return exercisePartRepository.findById(id).orElseThrow(()-> new RuntimeException("no such exercise"));
    }

    /**
     * Get all exercise parts.
     *
     * @return the list of exercises parts
     */
    public List<ExercisePart> getAll(){
        return exercisePartRepository.findAll();
    }

    /**
     * Insert exercise part.
     *
     * @param exercisePart the exercise part
     */
    public void insert(ExercisePart exercisePart) {
        customNativeRepository.insertExercisePart(exercisePart);
    }

    /**
     * Save exercise part.
     *
     * @param exercisePart the exercise part
     */
    public void save(ExercisePart exercisePart) {
        exercisePartRepository.save(exercisePart);
    }

    /**
     * Delete exercise part.
     *
     * @param exercisePart the exercise part
     */
    public void delete(ExercisePart exercisePart){
        exercisePartRepository.delete(exercisePart);
    }
}
