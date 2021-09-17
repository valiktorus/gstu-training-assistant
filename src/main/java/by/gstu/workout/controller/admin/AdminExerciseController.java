package by.gstu.workout.controller.admin;

import by.gstu.workout.model.Exercise;
import by.gstu.workout.model.ExercisePart;
import by.gstu.workout.model.Image;
import by.gstu.workout.service.EquipmentService;
import by.gstu.workout.service.ExercisePartService;
import by.gstu.workout.service.ExerciseService;
import by.gstu.workout.service.ImageService;
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
public class AdminExerciseController {
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private MuscleGroupService muscleGroupService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ExercisePartService exercisePartService;

    @GetMapping("/admin/exercise/list")
    public String listExercisePage(Model model){
        model.addAttribute("activeMenu", "side-menu-del-exercise");
        model.addAttribute("exercises", exerciseService.getAll());
        model.addAttribute("muscles", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/exercise/list-exercises";
    }

    @GetMapping("/admin/exercise/edit/{id}")
    public String editExercisePage(@PathVariable Long id, Model model) {
        model.addAttribute("activeMenu", "side-menu-del-exercise");
        model.addAttribute("exercise", exerciseService.get(id));
        model.addAttribute("muscles", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/exercise/edit-exercise";
    }

    @SneakyThrows
    @PostMapping("/admin/exercise/edit/{id}")
    public String editExercise(@PathVariable Long id,
                                 @RequestParam("muscle") Long muscleGroupId,
                                 @RequestParam("equipment") Long equipmentId,
                                 @RequestParam String description,
                                 @RequestParam("newImage") MultipartFile image,
                                 @RequestParam String name,
                                 Model model) {
        Exercise exercise = exerciseService.get(id);
        if (!image.isEmpty()) {
            Image exerciseImage = imageService.getById(exercise.getId());
            exerciseImage.setImage(image.getBytes());
            imageService.save(exerciseImage);
        }
        exercise.setDescription(description);
        exercise.setMuscleGroup(muscleGroupService.get(muscleGroupId));
        exercise.setEquipment(equipmentService.get(equipmentId));
        model.addAttribute("activeMenu", "side-menu-del-exercise");
        model.addAttribute("exercise", exerciseService.save(exercise));
        model.addAttribute("muscles", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/exercise/edit-exercise";
    }

    @SneakyThrows
    @PostMapping("/admin/exercisepart/edit/{id}")
    public String editExercisePart(@PathVariable Long id,
                             @RequestParam String description,
                             @RequestParam("newImage") MultipartFile image,
                             @RequestParam Integer order,
                             Model model) {
        ExercisePart exercisePart = exercisePartService.get(id);
        if (!image.isEmpty()) {
            exercisePart.getImage().setImage(image.getBytes());
        }
        exercisePart.setOrder(order);
        exercisePart.setDescription(description);
        exercisePartService.save(exercisePart);
        model.addAttribute("activeMenu", "side-menu-del-exercise");
        model.addAttribute("exercise", exerciseService.get(exercisePart.getExercise().getId()));
        model.addAttribute("muscles", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/exercise/edit-exercise";
    }


    @PostMapping("/admin/exercise/delete/{id}")
    public String deleteExercise(@PathVariable Long id, Model model) {
        Exercise exercise = exerciseService.get(id);
        exerciseService.delete(exercise);
        model.addAttribute("activeMenu", "side-menu-del-exercise");
        model.addAttribute("exercises", exerciseService.getAll());
        model.addAttribute("muscles", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/exercise/list-exercises";
    }

    @PostMapping("/admin/exercisepart/delete/{id}")
    public String deleteExercisePart(@PathVariable Long id, Model model) {
        ExercisePart exercisePart = exercisePartService.get(id);
        exercisePartService.delete(exercisePart);
        model.addAttribute("activeMenu", "side-menu-del-exercise");
        model.addAttribute("exercise", exercisePart.getExercise());
        model.addAttribute("muscles", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/exercise/edit-exercise";
    }

    @GetMapping("/admin/exercise/add")
    public String addExercisePage(Model model) {
        model.addAttribute("activeMenu", "side-menu-add-exercise");
        model.addAttribute("muscles", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/exercise/add-exercise";
    }

    @PostMapping("/admin/exercise/add")
    public String addExercise(@RequestParam String name,
                            @RequestParam("newImage") MultipartFile image,
                            @RequestParam("muscle") Long muscleGroupId,
                            @RequestParam("equipment") Long equipmentId,
                            @RequestParam String description,
                            Model model) {

        model.addAttribute("activeMenu", "side-menu-del-exercise");
        model.addAttribute("exercise", exerciseService.save(name, image, muscleGroupId,
                equipmentId, description));
        model.addAttribute("muscles", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/exercise/edit-exercise";
    }

    @SneakyThrows
    @PostMapping("/admin/exercisepart/add/{id}")
    public String addExercisePart(@PathVariable("id") Long exerciseId,
                                @RequestParam("newImage") MultipartFile imageFile,
                            @RequestParam Integer order,
                            @RequestParam String description,
                            Model model) {
        ExercisePart exercisePart = new ExercisePart();
        exercisePart.setExercise(exerciseService.get(exerciseId));

        if (!imageFile.isEmpty()){
            Image image = new Image();
            image.setImage(imageFile.getBytes());
            Image savedImage = imageService.save(image);
            exercisePart.setImage(savedImage);
        }
        exercisePart.setOrder(order);
        exercisePart.setDescription(description);
        exercisePartService.insert(exercisePart);
        model.addAttribute("activeMenu", "side-menu-del-exercise");
        model.addAttribute("exercise", exerciseService.get(exerciseId));
        model.addAttribute("muscles", muscleGroupService.getAll());
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/exercise/edit-exercise";
    }
}
