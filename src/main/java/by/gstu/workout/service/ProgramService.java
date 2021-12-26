package by.gstu.workout.service;

import by.gstu.workout.Constants;
import by.gstu.workout.enums.DifficultyName;
import by.gstu.workout.model.Program;
import by.gstu.workout.repository.ProgramRepository;
import by.gstu.workout.service.sorting.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Program service.
 */
@Service
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private SortingService sortingService;
    @Autowired
    private ImageService imageService;

    /**
     * Get program.
     *
     * @param id the id
     * @return the program
     */
    public Program get(Long id) {
        return programRepository.findById(id).orElseThrow(() -> new RuntimeException("No such Program"));
    }

    /**
     * Get all programs.
     *
     * @return the list
     */
    public List<Program> getAll(){
        return programRepository.findAll();
    }

    /**
     * Gets all programs by difficulty.
     *
     * @param difficulty the difficulty
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortedField the sorted field
     * @param direction the direction
     * @return the all programs by difficulty
     */
    public Page<Program> getAllByDifficulty(String difficulty, int pageNumber,
                                            int pageSize, String sortedField, Sort.Direction direction) {
        String currentDifficulty = Constants.DEFAULT_FILTRATION_VALUE.equals(difficulty) ? null : difficulty;
        String currentSortedBy = Constants.DEFAULT_SORTING_VALUE.equals(sortedField) ? "id" :
                sortingService.getConvertedProgramSortingField(sortedField);
        Pageable pageRequest = PageRequest.of(pageNumber - 1, pageSize, direction, currentSortedBy);
        if (Objects.isNull(currentDifficulty)) {
            return programRepository.findAll(pageRequest);
        }
        return programRepository.findAllByDifficultyName(DifficultyName.valueOf(difficulty), pageRequest);
    }

    /**
     * Save program.
     *
     * @param program the program
     * @return the program
     */
    public Program save(Program program) {
        return programRepository.save(program);
    }

    /**
     * Delete program.
     *
     * @param programId the program id
     */
    public void delete(Long programId) {
        programRepository.delete(get(programId));
    }
}
