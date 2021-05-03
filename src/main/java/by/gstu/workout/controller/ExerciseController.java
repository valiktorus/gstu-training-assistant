package by.gstu.workout.controller;

import by.gstu.workout.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;
    @GetMapping("/exercise/{id}")
 public String getSingleExercisePage(@PathVariable Long id, Model model){
     model.addAttribute("exercise", exerciseService.get(id));
     return "single-exercise";
 }

}
