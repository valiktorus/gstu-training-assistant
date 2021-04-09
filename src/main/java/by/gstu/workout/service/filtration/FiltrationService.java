package by.gstu.workout.service.filtration;

import by.gstu.workout.enums.DifficultyName;
import by.gstu.workout.model.Difficulty;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
public class FiltrationService {
    @Value("${service.default-filtration-value}")
    private String defaultFiltrationValue;

    public String getCurrentEquipment(String equipment) {
        return equipment.equals(defaultFiltrationValue) ? "" : equipment;
    }

    public String getSelectedEquipment(String equipment) {
        return equipment.isEmpty() ? defaultFiltrationValue : equipment;
    }

    public String getCurrentMuscleGroup(String muscleGroup) {
        return muscleGroup.equals(defaultFiltrationValue) ? "" : muscleGroup;
    }

    public String getSelectedMuscleGroup(String muscleGroup) {
        return muscleGroup.isEmpty() ? defaultFiltrationValue : muscleGroup;
    }

    public String getCurrentDifficulty(String difficulty) {
        return difficulty.equals(defaultFiltrationValue) ? "" : difficulty;
    }

    public String getSelectedDifficulty(String difficulty) {
        return difficulty.isEmpty() ? defaultFiltrationValue : difficulty;
    }
}
