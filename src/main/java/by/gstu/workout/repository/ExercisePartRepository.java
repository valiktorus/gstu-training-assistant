package by.gstu.workout.repository;

import by.gstu.workout.model.ExercisePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * The interface Exercise part repository.
 */
public interface ExercisePartRepository extends JpaRepository<ExercisePart, Long> {

    /**
     * Save exercise part.
     *
     * @param exerciseId the exercise id
     * @param order the order
     * @param imageId the image id
     * @param description the description
     */
    @Query(value = "Insert into fitness.exercise_part (`exercise_id`, `order`, `image_id`, `description`) VALUES (11, 2, 11, 'qwe')",
            nativeQuery = true)
     void savePart(@Param("id") Long exerciseId,
                         @Param("order") Integer order,
                         @Param("image") Long imageId,
                         @Param("description") String description);
}
