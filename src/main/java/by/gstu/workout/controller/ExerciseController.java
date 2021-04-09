package by.gstu.workout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ExerciseController {
    @GetMapping("/exercise/{id}")
 public String getSingleExercisePage(@PathVariable Long id, Model model){
     return "single-exercise";
 }

}
