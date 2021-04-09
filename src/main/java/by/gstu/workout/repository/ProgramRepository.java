package by.gstu.workout.repository;

import by.gstu.workout.enums.DifficultyName;
import by.gstu.workout.model.Difficulty;
import by.gstu.workout.model.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    Page<Program> findAllBy(Pageable pageable);
    Page<Program> findAllByDifficultyDifficultyNameContaining(DifficultyName difficulty, Pageable pageable);

}
