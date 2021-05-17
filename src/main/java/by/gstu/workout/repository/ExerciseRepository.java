package by.gstu.workout.repository;

import by.gstu.workout.model.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Page<Exercise> findAll(Pageable pageable);
    Page<Exercise> findAllByMuscleGroupName(String muscleGroup, Pageable pageable);
    Page<Exercise> findAllByEquipmentName(String equipment, Pageable pageable);
    Page<Exercise> findAllByMuscleGroupNameAndEquipmentName(String muscleGroup, String equipment,
                                                            Pageable pageable);
}
