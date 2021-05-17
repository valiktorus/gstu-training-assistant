package by.gstu.workout.service.sorting;

import by.gstu.workout.Constants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Getter
public class SortingService {
    @Value("${service.default-sorting-value}")
    private String defaultSortingField;
    private final HashMap<String, String> exerciseSortingFields = new HashMap<>();
    private final HashMap<String, String> programSortingFields = new HashMap<>();

    @PostConstruct
    private void postConstruct() {
        exerciseSortingFields.put("снаряжение", "equipment.name");
        exerciseSortingFields.put("имя", "name");
        programSortingFields.put("сложность", "difficulty.id");
        programSortingFields.put("имя", "name");

    }

    public List<String> getExerciseSortingFields() {
        List<String> sortingFields = new ArrayList<>();
        sortingFields.add(Constants.DEFAULT_SORTING_VALUE);
        sortingFields.addAll(exerciseSortingFields.keySet());
        return sortingFields;
    }

    public String getConvertedExerciseSortingField(String sortingField) {
        return exerciseSortingFields.get(sortingField);
    }

    public List<String> getProgramSortingFields() {
        List<String> sortingFields = new ArrayList<>();
        sortingFields.add(Constants.DEFAULT_SORTING_VALUE);
        sortingFields.addAll(programSortingFields.keySet());
        return sortingFields;
    }

    public String getConvertedProgramSortingField(String sortingField) {
        return programSortingFields.get(sortingField);
    }
}
