package by.gstu.workout.repository;

import by.gstu.workout.model.ExercisePart;
import by.gstu.workout.model.ProgramSegment;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 * Custom native repository which uses sql.
 */
@Repository
public class CustomNativeRepositoryImpl{

    @Autowired
    private EntityManager entityManager;

    /**
     * Insert exercise part using sql.
     *
     * @param exercisePart the exercise part
     */
    @SneakyThrows
    @Transactional
    public void insertExercisePart(ExercisePart exercisePart){
        Query nativeQuery = entityManager.createNativeQuery("insert into fitness.exercise_part (exercise_id, `order`, image_id, description)\n" +
                "VALUES (?, ?, ?, ?)");
        nativeQuery.setParameter(1, exercisePart.getExercise().getId());
        nativeQuery.setParameter(2, exercisePart.getOrder());
        nativeQuery.setParameter(3, exercisePart.getImage().getId());
        nativeQuery.setParameter(4, exercisePart.getDescription());
        nativeQuery.executeUpdate();
    }

    /**
     * Insert program segment using sql.
     *
     * @param programSegment the program segment
     */
    @SneakyThrows
    @Transactional
    public void insertProgramSegment(ProgramSegment programSegment){
        Query nativeQuery = entityManager.createNativeQuery("insert into fitness.program_segment " +
                "(program_id, exercise_id, element_time_seconds, repetitions, `order`, rest_time_seconds, type_id, sets_number)\n" +
                "VALUES (?,?,?,?,?,?,?,?)");
        nativeQuery.setParameter(1, programSegment.getProgram().getId());
        nativeQuery.setParameter(2, programSegment.getExercise().getId());
        nativeQuery.setParameter(3, programSegment.getElementRestTimeSeconds());
        nativeQuery.setParameter(4, programSegment.getRepetitions());
        nativeQuery.setParameter(5, programSegment.getOrder());
        nativeQuery.setParameter(6, programSegment.getRestTimeSeconds());
        nativeQuery.setParameter(7, programSegment.getProgramSegmentType());
        nativeQuery.setParameter(8, programSegment.getSetsNumber());
        nativeQuery.executeUpdate();
    }
}
