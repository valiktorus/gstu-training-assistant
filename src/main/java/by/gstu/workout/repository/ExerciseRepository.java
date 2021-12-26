package by.gstu.workout.repository;

import by.gstu.workout.model.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * The interface Exercise repository.
 */
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Page<Exercise> findAll(Pageable pageable);

    /**
     * Find all by muscle group name page.
     *
     * @param muscleGroup the muscle group
     * @param pageable the pageable
     * @return  the page of exercises
     */
    Page<Exercise> findAllByMuscleGroupName(String muscleGroup, Pageable pageable);

    /**
     * Find all by equipment name page.
     *
     * @param equipment the equipment
     * @param pageable the pageable
     * @return  the page of exercises
     */
    Page<Exercise> findAllByEquipmentName(String equipment, Pageable pageable);

    /**
     * Find all by muscle group name and equipment name page.
     *
     * @param muscleGroup the muscle group
     * @param equipment the equipment
     * @param pageable the pageable
     * @return the page of exercises
     */
    Page<Exercise> findAllByMuscleGroupNameAndEquipmentName(String muscleGroup, String equipment,
                                                            Pageable pageable);
}
