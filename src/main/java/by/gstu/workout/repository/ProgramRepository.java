package by.gstu.workout.repository;

import by.gstu.workout.enums.DifficultyName;
import by.gstu.workout.model.Difficulty;
import by.gstu.workout.model.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Program repository.
 */
public interface ProgramRepository extends JpaRepository<Program, Long> {
    /**
     * Find all by page.
     *
     * @param pageable the pageable
     * @return the page of programs
     */
    Page<Program> findAllBy(Pageable pageable);

    /**
     * Find all by difficulty name page.
     *
     * @param difficulty the difficulty
     * @param pageable the pageable
     * @return the page of programs
     */
    Page<Program> findAllByDifficultyName(DifficultyName difficulty, Pageable pageable);
}
