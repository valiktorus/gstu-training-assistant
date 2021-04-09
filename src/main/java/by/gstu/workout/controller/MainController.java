package by.gstu.workout.controller;

import by.gstu.workout.model.Exercise;
import by.gstu.workout.model.Program;
import by.gstu.workout.model.ProgramSegment;
import by.gstu.workout.service.*;
import by.gstu.workout.service.filtration.FiltrationService;
import by.gstu.workout.service.sorting.SortingService;
import by.gstu.workout.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
    @Autowired
    private DifficultyService difficultyService;
    @Autowired
    private ProgramService programService;


    @GetMapping(value = {"/", "/home", "/exercises"})
    public String getExercises(@RequestParam Optional<Integer> page,
                               @RequestParam Optional<Integer> pageSize,
                               @RequestParam(defaultValue = "") String muscleGroup,
                               @RequestParam(defaultValue = "") String equipment,
                               @RequestParam(defaultValue = "id") String sortedBy,
                               Model model) {
        int currentPage = page.orElse(1);
        int currentPageSize = pageSize.orElse(2);
        String currentMuscleGroup = filtrationService.getCurrentMuscleGroup(muscleGroup);
        String currentEquipment = filtrationService.getCurrentEquipment(equipment);
        Page<Exercise> exercisesPage = exerciseService.getAllByMuscleGroupAndEquipment(currentMuscleGroup, currentEquipment,
                currentPage - 1, currentPageSize, sortedBy, Sort.Direction.ASC);
        Pager<Exercise> pager = new Pager<>(exercisesPage);
        model.addAttribute("pager", pager);
        model.addAttribute("exercises", exercisesPage.getContent());
        model.addAttribute("muscleGroups", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        model.addAttribute("defaultFiltrationValue", filtrationService.getDefaultFiltrationValue());
        model.addAttribute("defaultSortingValue", sortingService.getDefaultSortingField());
        model.addAttribute("sortingFields", sortingService.getExerciseSortingFields());
        model.addAttribute("selectedEquipment", filtrationService.getSelectedEquipment(equipment));
        model.addAttribute("selectedMuscleGroup", filtrationService.getSelectedMuscleGroup(muscleGroup));
        model.addAttribute("selectedSortingField", sortedBy);
        return "exercises";
    }

    @GetMapping(value = {"/programs"})
    public String getPrograms(@RequestParam Optional<Integer> page,
                              @RequestParam Optional<Integer> pageSize,
                              @RequestParam(defaultValue = "") String difficulty,
                              @RequestParam(defaultValue = "id") String sortedBy,
                              Model model) {
        int currentPage = page.orElse(1);
        int currentPageSize = pageSize.orElse(2);
        String currentDifficulty = filtrationService.getCurrentDifficulty(difficulty);
//        String sortedField = sortingService.getCurrentSortingField(sortedBy);
        Page<Program> programPage;
        if (currentDifficulty.isEmpty()) {
            programPage = programService.getAll(currentPage - 1, currentPageSize, sortedBy, Sort.Direction.ASC);
        } else {
            programPage = programService.getAllByDifficulty(currentDifficulty, currentPage - 1, currentPageSize, sortedBy, Sort.Direction.ASC);
        }
        Pager<Program> pager = new Pager<>(programPage);
        model.addAttribute("pager", pager);
        model.addAttribute("programs", programPage.getContent());
        model.addAttribute("difficulties", difficultyService.getAll());
        model.addAttribute("defaultFiltrationValue", filtrationService.getDefaultFiltrationValue());
        model.addAttribute("defaultSortingValue", sortingService.getDefaultSortingField());
        model.addAttribute("sortingFields", sortingService.getProgramSortingFields());
        model.addAttribute("selectedDifficulty", filtrationService.getSelectedDifficulty(difficulty));
        model.addAttribute("selectedSortingField", sortedBy);
        return "programs";
    }
}
