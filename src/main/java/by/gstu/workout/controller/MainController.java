package by.gstu.workout.controller;

import by.gstu.workout.model.Exercise;
import by.gstu.workout.service.EquipmentService;
import by.gstu.workout.service.ExerciseService;
import by.gstu.workout.service.MuscleGroupService;
import by.gstu.workout.service.filtration.FiltrationService;
import by.gstu.workout.service.sorting.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private MuscleGroupService muscleGroupService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private SortingService sortingService;
    @Autowired
    private FiltrationService filtrationService;


    @GetMapping(value = {"/", "/home"})
    public String booksPaging(@RequestParam Optional<Integer> page,
                              @RequestParam Optional<Integer> pageSize,
                              @RequestParam Optional<String> muscleGroup,
                              @RequestParam Optional<String> equipment,
                              @RequestParam Optional<String> sortedBy,
                              Model model) {
        int currentPage = page.orElse(1);
        int currentPageSize = pageSize.orElse(2);
        String currentMuscleGroup = muscleGroup.orElse("");
        String currentEquipment = equipment.orElse("");
        String sortedField = sortedBy.orElse("");
        Page<Exercise> exercisesPage = exerciseService.getAll(0, 50);
        model.addAttribute("exercises", exercisesPage.getContent());
        model.addAttribute("muscleGroups", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        model.addAttribute("defaultFiltrationValue", filtrationService.getDefaultFiltrationValue());
        model.addAttribute("defaultSortingValue", sortingService.getDefaultSortingField());
        model.addAttribute("sortingFields", sortingService.getExerciseSortingFields());
        return "exercises";
    }
}
