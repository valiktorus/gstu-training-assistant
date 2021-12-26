package by.gstu.workout.service;

import by.gstu.workout.model.Difficulty;
import by.gstu.workout.repository.DifficultyRepository;
import jdk.internal.org.jline.utils.DiffHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Difficulty service.
 */
@Service
public class DifficultyService {
    @Autowired
    private DifficultyRepository difficultyRepository;

    /**
     * Gets all difficulties.
     *
     * @return all difficulties
     */
    public List<Difficulty> getAll() {
        return difficultyRepository.findAll();
    }

    /**
     * Get difficulty.
     *
     * @param id the id
     * @return the difficulty
     */
    public Difficulty get(Long id) {
        return difficultyRepository.getOne(id);
    }

    /**
     * Save difficulty.
     *
     * @param difficulty the difficulty
     * @return the difficulty
     */
    public Difficulty save(Difficulty difficulty){
        return difficultyRepository.save(difficulty);
    }

    /**
     * Delete difficulty.
     *
     * @param difficulty the difficulty
     */
    public void delete(Difficulty difficulty) {
        difficultyRepository.delete(difficulty);
    }
}
