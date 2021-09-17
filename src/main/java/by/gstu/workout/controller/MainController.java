package by.gstu.workout.controller;

import by.gstu.workout.Constants;
import by.gstu.workout.model.Exercise;
import by.gstu.workout.model.Program;
import by.gstu.workout.service.*;
import by.gstu.workout.service.sorting.SortingService;
import by.gstu.workout.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private DifficultyService difficultyService;
    @Autowired
    private ProgramService programService;


    @GetMapping(value = {"/", "/home", "/exercises"} )
    public String getExercises(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "6") Integer pageSize,
                               @RequestParam(defaultValue = Constants.DEFAULT_FILTRATION_VALUE) String muscleGroup,
                               @RequestParam(defaultValue = Constants.DEFAULT_FILTRATION_VALUE) String equipment,
                               @RequestParam(defaultValue = Constants.DEFAULT_SORTING_VALUE) String sortedBy,
                               OAuth2AuthenticationToken token,
                                           Model model) {
        Page<Exercise> exercisesPage = exerciseService.getAllByMuscleGroupAndEquipment(muscleGroup, equipment,
                page, pageSize, sortedBy, Sort.Direction.ASC);
        Pager<Exercise> pager = new Pager<>(exercisesPage);
        model.addAttribute("pager", pager);
        model.addAttribute("exercises", exercisesPage.getContent());
        model.addAttribute("muscleGroups", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        model.addAttribute("defaultFiltrationValue", Constants.DEFAULT_FILTRATION_VALUE);
        model.addAttribute("defaultSortingValue", Constants.DEFAULT_SORTING_VALUE);
        model.addAttribute("sortingFields", sortingService.getExerciseSortingFields());
        model.addAttribute("selectedEquipment", equipment);
        model.addAttribute("selectedMuscleGroup", muscleGroup);
        model.addAttribute("selectedSortingField", sortedBy);
        model.addAttribute("userName", ((DefaultOidcUser) token.getPrincipal()).getGivenName());
        return "exercises";
    }

    @GetMapping(value = {"/programs"})
    public String getPrograms(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "6") Integer pageSize,
                              @RequestParam(defaultValue = Constants.DEFAULT_FILTRATION_VALUE) String difficulty,
                              @RequestParam(defaultValue = Constants.DEFAULT_SORTING_VALUE) String sortedBy,
                              OAuth2AuthenticationToken token,
                              Model model) {
        Page<Program> programPage = programService.getAllByDifficulty(difficulty, page, pageSize, sortedBy, Sort.Direction.ASC);
        Pager<Program> pager = new Pager<>(programPage);
        model.addAttribute("pager", pager);
        model.addAttribute("programs", programPage.getContent());
        model.addAttribute("difficulties", difficultyService.getAll());
        model.addAttribute("defaultFiltrationValue", Constants.DEFAULT_FILTRATION_VALUE);
        model.addAttribute("defaultSortingValue", sortingService.getDefaultSortingField());
        model.addAttribute("sortingFields", sortingService.getProgramSortingFields());
        model.addAttribute("selectedDifficulty", difficulty);
        model.addAttribute("selectedSortingField", sortedBy);
        model.addAttribute("userName", ((DefaultOidcUser) token.getPrincipal()).getGivenName());
        return "programs";
    }
}
