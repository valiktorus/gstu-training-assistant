package by.gstu.workout.service;

import by.gstu.workout.model.Exercise;
import by.gstu.workout.model.ExercisePart;
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
    private ExerciseRepository exerciseRepository;

    public ExercisePart get(long id) {
        return exercisePartRepository.findById(id).orElseThrow(()-> new RuntimeException("no such exercise"));
    }
    public List<ExercisePart> getAll(){
        return exercisePartRepository.findAll();
    }

    public ExercisePart save(ExercisePart exercisePart) {
        System.out.println("");
        exerciseRepository.savePart();
        exercisePartRepository.savePart(exercisePart.getExercise().getId(), exercisePart.getOrder(),
                exercisePart.getImage().getId(),exercisePart.getDescription());
        ExercisePart save = exercisePartRepository.save(exercisePart);
        return save;
    }
}
