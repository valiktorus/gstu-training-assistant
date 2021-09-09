package by.gstu.workout.repository;

import by.gstu.workout.model.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Page<Exercise> findAll(Pageable pageable);
    Page<Exercise> findAllByMuscleGroupName(String muscleGroup, Pageable pageable);
    Page<Exercise> findAllByEquipmentName(String equipment, Pageable pageable);
    Page<Exercise> findAllByMuscleGroupNameAndEquipmentName(String muscleGroup, String equipment,
                                                            Pageable pageable);

    @Query(value = "Insert into fitness.exercise_part (`exercise_id`, `order`, `image_id`, `description`) VALUES (11, 2, 11, 'qwe')",
            nativeQuery = true)
    void savePart();
}
