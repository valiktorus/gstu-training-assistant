package by.gstu.workout.repository;

import by.gstu.workout.model.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Page<Exercise> findAllByMuscleGroupNameContainingAndEquipmentNameContaining(String muscleGroup, String equipment,
                                                                                       Pageable pageable);
}
