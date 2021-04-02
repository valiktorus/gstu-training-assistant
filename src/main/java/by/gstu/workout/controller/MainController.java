package by.gstu.workout.controller;

import by.gstu.workout.model.Equipment;
import by.gstu.workout.model.Exercise;
import by.gstu.workout.model.Image;
import by.gstu.workout.model.Program;
import by.gstu.workout.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private MuscleGroupRepository muscleGroupRepository;
    @Autowired
    private DifficultyRepository difficultyRepository;
    @Autowired
    private ProgramSegmentTypeRepository programSegmentTypeRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private ExercisePartRepository exercisePartRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private ProgramSegmentRepository programSegmentRepository;

    @GetMapping(value = {"/", "/home"})
    public String booksPaging(Model model) {
        Optional<Image> byId = imageRepository.findById(1L);
        System.out.println(byId);
        return "index";
    }
}
