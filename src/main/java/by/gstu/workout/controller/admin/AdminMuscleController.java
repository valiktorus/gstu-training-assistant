package by.gstu.workout.controller.admin;

import by.gstu.workout.model.MuscleGroup;
import by.gstu.workout.service.MuscleGroupService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminMuscleController {
    @Autowired
    private MuscleGroupService muscleGroupService;

    @GetMapping("/admin/muscle/list")
    public String listMusclePage(Model model){
        model.addAttribute("activeMenu", "side-menu-del-muscle");
        model.addAttribute("muscles", muscleGroupService.getAll());
        return "admin/muscles/list-muscles";
    }

    @GetMapping("/admin/muscle/edit/{id}")
    public String editMusclePage(@PathVariable Long id, Model model) {
        model.addAttribute("activeMenu", "side-menu-del-muscle");
        model.addAttribute("muscle", muscleGroupService.get(id));
        return "admin/muscles/edit-muscle";
    }

    @SneakyThrows
    @PostMapping("/admin/muscle/edit/{id}")
    public String editMuscle(@PathVariable Long id,
                                 @RequestParam("newImage") MultipartFile image,
                                 @RequestParam String name,
                                 Model model) {
        MuscleGroup previousMuscleGroup = muscleGroupService.get(id);
        if (!image.isEmpty()) {
            previousMuscleGroup.getImage().setImage(image.getBytes());
        }
        previousMuscleGroup.setName(name);
        muscleGroupService.save(previousMuscleGroup);
        model.addAttribute("activeMenu", "side-menu-del-muscle");
        model.addAttribute("muscles", muscleGroupService.getAll());
        return "admin/muscles/list-muscles";
    }


    @PostMapping("/admin/muscle/delete/{id}")
    public String deleteMuscle(@PathVariable Long id, Model model) {
        MuscleGroup muscleGroup = muscleGroupService.get(id);
        muscleGroupService.delete(muscleGroup);
        model.addAttribute("activeMenu", "side-menu-del-muscle");
        model.addAttribute("muscles", muscleGroupService.getAll());
        return "admin/muscles/list-muscles";
    }

    @GetMapping("/admin/muscle/add")
    public String addMusclePage(Model model) {
        model.addAttribute("activeMenu", "side-menu-add-muscle");
        return "admin/muscles/add-muscle";
    }

    @PostMapping("/admin/muscle/add")
    public String addMuscle(@RequestParam String name, @RequestParam("newImage") MultipartFile image, Model model) {
        muscleGroupService.save(name, image);
        model.addAttribute("activeMenu", "side-menu-del-muscle");
        model.addAttribute("muscles", muscleGroupService.getAll());
        return "admin/muscles/list-muscles";
    }
}
