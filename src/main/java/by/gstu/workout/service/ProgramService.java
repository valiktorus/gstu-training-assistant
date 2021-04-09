package by.gstu.workout.service;

import by.gstu.workout.enums.DifficultyName;
import by.gstu.workout.model.Program;
import by.gstu.workout.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;

    public Page<Program> getAll(int pageNumber, int pageSize, String sortedField, Sort.Direction direction){
            return programRepository.findAllBy(PageRequest.of(pageNumber, pageSize, direction, sortedField));
    }
    public Page<Program> getAllByDifficulty(String difficulty,  int pageNumber,
                                                         int pageSize, String sortedField, Sort.Direction direction) {
        return programRepository.findAllByDifficultyDifficultyName(DifficultyName.valueOf(difficulty),
                PageRequest.of(pageNumber, pageSize, direction, sortedField));
    }
}
