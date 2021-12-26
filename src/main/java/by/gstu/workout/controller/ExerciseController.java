package by.gstu.workout.controller;

import by.gstu.workout.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Exercise controller.
 */
@Controller
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    /**
     * Get single exercise page.
     *
     * @param id the id
     * @param model the model
     * @param token the token
     * @return single exercise page
     */
    @GetMapping("/exercise/{id}")
 public String getSingleExercisePage(@PathVariable Long id, Model model, OAuth2AuthenticationToken token){
     model.addAttribute("exercise", exerciseService.get(id));
     model.addAttribute("userName", ((DefaultOidcUser) token.getPrincipal()).getGivenName());
     return "single-exercise";
 }

}
