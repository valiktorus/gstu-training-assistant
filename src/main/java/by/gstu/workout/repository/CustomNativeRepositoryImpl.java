package by.gstu.workout.repository;

import by.gstu.workout.model.ExercisePart;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
public class CustomNativeRepositoryImpl{

    @Autowired
    private EntityManager entityManager;


    @SneakyThrows
    @Transactional()
    public void runNativeQuery() {
        entityManager.createNativeQuery("insert into fitness.exercise_part (exercise_id, `order`, image_id, description)\n" +
                "VALUES (11, 1, 4, 'qwe')").executeUpdate();

    }

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
}
