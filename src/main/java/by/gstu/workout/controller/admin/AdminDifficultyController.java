package by.gstu.workout.controller.admin;

import by.gstu.workout.model.Difficulty;
import by.gstu.workout.service.DifficultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Admin difficulty controller.
 */
@Controller
public class AdminDifficultyController {
    @Autowired
    private DifficultyService difficultyService;

    /**
     * get delete or update difficulty page.
     *
     * @param model the model
     * @return update difficulty page
     */
    @GetMapping("/admin/difficulty/list")
    public String deleteUpdateDifficultyPage(Model model){
        model.addAttribute("activeMenu", "side-menu-del-dif");
        model.addAttribute("difficulties", difficultyService.getAll());
        return "admin/difficulties/list-difficulties";
    }

    /**
     * forward to edit difficulty page.
     *
     * @param id the id
     * @param model the model
     * @return edit difficulty page
     */
    @GetMapping("/admin/difficulty/edit/{id}")
    public String editDifficultyPage(@PathVariable Long id, Model model) {
        model.addAttribute("activeMenu", "side-menu-del-dif");
        model.addAttribute("difficulty", difficultyService.get(id));
        return "admin/difficulties/edit-difficulty";
    }

    /**
     * Edit difficulty.
     *
     * @param id the id
     * @param name the name
     * @param model the model
     * @return list difficulty page
     */
    @PostMapping("/admin/difficulty/edit/{id}")
    public String editDifficulty(@PathVariable Long id,
                                 @RequestParam String name,
                                 Model model) {
        Difficulty difficulty = difficultyService.get(id);
        difficulty.setName(name);
        difficultyService.save(difficulty);
        model.addAttribute("activeMenu", "side-menu-del-dif");
        model.addAttribute("difficulties", difficultyService.getAll());
        return "admin/difficulties/list-difficulties";
    }


    /**
     * Delete difficulty.
     *
     * @param id the id
     * @param model the model
     * @return list difficulty page
     */
    @PostMapping("/admin/difficulty/delete/{id}")
    public String deleteDifficulty(@PathVariable Long id, Model model) {
        Difficulty difficulty = difficultyService.get(id);
        difficultyService.delete(difficulty);
        model.addAttribute("activeMenu", "side-menu-del-dif");
        model.addAttribute("difficulties", difficultyService.getAll());
        return "admin/difficulties/list-difficulties";
    }

    /**
     * forward to add difficulty page.
     *
     * @param model the model
     * @return add difficulty page
     */
    @GetMapping("/admin/difficulty/add")
    public String addDifficultyPage(Model model) {
        model.addAttribute("activeMenu", "side-menu-add-dif");
        model.addAttribute("difficulties", difficultyService.getAll());
        return "admin/difficulties/add-difficulty";
    }

    /**
     * Add difficulty.
     *
     * @param name the name
     * @param model the model
     * @return list difficulty page
     */
    @PostMapping("/admin/difficulty/add")
    public String addDifficulty(@RequestParam String name, Model model) {
        Difficulty difficulty = new Difficulty();
        difficulty.setName(name);
        difficultyService.save(difficulty);
        model.addAttribute("activeMenu", "side-menu-del-dif");
        model.addAttribute("difficulties", difficultyService.getAll());
        return "admin/difficulties/list-difficulties";
    }
}
