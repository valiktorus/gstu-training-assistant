package by.gstu.workout.service.sorting;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class SortingService {
    @Value("${service.default-sorting-value}")
    private String defaultSortingField;

    public List<String> getExerciseSortingFields() {
        return List.of("id", "name", "equipment_id");
    }
    public List<String> getProgramSortingFields() {
        return List.of( "name", "difficulty_id");
    }

    public String getCurrentSortingField(String sortingField) {
        return sortingField.equals(defaultSortingField) ? "id" : sortingField;
    }

//    public String getSelectedSortingField(String sortingField) {
//        return equipment.isEmpty() ? defaultFiltrationValue : equipment;
//    }
}
