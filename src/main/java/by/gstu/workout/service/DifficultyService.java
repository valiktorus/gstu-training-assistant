package by.gstu.workout.service;

import by.gstu.workout.model.Difficulty;
import by.gstu.workout.repository.DifficultyRepository;
import jdk.internal.org.jline.utils.DiffHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DifficultyService {
    @Autowired
    private DifficultyRepository difficultyRepository;

    public List<Difficulty> getAll() {
        return difficultyRepository.findAll();
    }

    public Difficulty get(Long id) {
        return difficultyRepository.getOne(id);
    }

    public Difficulty save(Difficulty difficulty){
        return difficultyRepository.save(difficulty);
    }

    public void delete(Difficulty difficulty) {
        difficultyRepository.delete(difficulty);
    }
}
