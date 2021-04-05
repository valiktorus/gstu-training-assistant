package by.gstu.workout.controller;

import by.gstu.workout.model.Equipment;
import by.gstu.workout.model.Exercise;
import by.gstu.workout.model.Image;
import by.gstu.workout.model.Program;
import by.gstu.workout.repository.*;
import by.gstu.workout.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ExerciseService exerciseService;

    @GetMapping(value = {"/", "/home"})
    public String booksPaging(Model model) {
        Page<Exercise> page = exerciseService.getAll(0, 50);
        model.addAttribute("exersises", page.getContent());
        return "exercises";
    }
}
