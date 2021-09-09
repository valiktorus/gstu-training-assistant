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

import java.util.Objects;

@Service
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private SortingService sortingService;
    @Autowired
    private ImageService imageService;

    public Program get(Long id) {
        return programRepository.findById(id).orElseThrow(() -> new RuntimeException("No such Program"));
    }

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
}
