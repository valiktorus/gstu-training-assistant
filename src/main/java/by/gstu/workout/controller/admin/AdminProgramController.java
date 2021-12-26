package by.gstu.workout.controller.admin;

import by.gstu.workout.model.Image;
import by.gstu.workout.model.Program;
import by.gstu.workout.model.ProgramSegment;
import by.gstu.workout.repository.CustomNativeRepositoryImpl;
import by.gstu.workout.service.DifficultyService;
import by.gstu.workout.service.EquipmentService;
import by.gstu.workout.service.ExercisePartService;
import by.gstu.workout.service.ExerciseService;
import by.gstu.workout.service.ImageService;
import by.gstu.workout.service.ProgramSegmentService;
import by.gstu.workout.service.ProgramService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Admin program controller.
 */
@Controller
public class AdminProgramController {
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private DifficultyService difficultyService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ExercisePartService exercisePartService;
    @Autowired
    private ProgramService programService;
    @Autowired
    private ProgramSegmentService programSegmentService;

    @Autowired
    private CustomNativeRepositoryImpl customNativeRepository;

    /**
     * forward to List program page string.
     *
     * @param model the model
     * @return program list page
     */
    @GetMapping("/admin/program/list")
    public String listProgramPage(Model model) {
        model.addAttribute("activeMenu", "side-menu-del-program");
        model.addAttribute("programs", programService.getAll());
        return "admin/program/list-programs";
    }

    /**
     * Edit program page string.
     *
     * @param id the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/admin/program/edit/{id}")
    public String editProgramPage(@PathVariable Long id, Model model) {
        model.addAttribute("activeMenu", "side-menu-del-program");
        model.addAttribute("program", programService.get(id));
        model.addAttribute("difficulties", difficultyService.getAll());
        model.addAttribute("exercises", exerciseService.getAll());
        return "admin/program/edit-program";
    }

    /**
     * Edit program.
     *
     * @param id the id
     * @param difficultyId the difficulty id
     * @param restTimeSeconds the rest time seconds
     * @param description the description
     * @param image the image
     * @param name the name
     * @param model the model
     * @return edit program page
     */
    @SneakyThrows
    @PostMapping("/admin/program/edit/{id}")
    public String editProgram(@PathVariable Long id,
                                 @RequestParam("difficulty") Long difficultyId,
                                 @RequestParam("rest-time") Integer restTimeSeconds,
                                 @RequestParam String description,
                                 @RequestParam("newImage") MultipartFile image,
                                 @RequestParam String name,
                                 Model model) {
        Program program = programService.get(id);
        if (!image.isEmpty()) {
            program.getImage().setImage(image.getBytes());
        }
        program.setDifficulty(difficultyService.get(difficultyId));
        program.setRestTimeSeconds(restTimeSeconds);
        program.setDescription(description);
        program.setName(name);
        programService.save(program);
        model.addAttribute("activeMenu", "side-menu-del-program");
        model.addAttribute("program", programService.save(program));
        model.addAttribute("difficulties", difficultyService.getAll());
        model.addAttribute("exercises", exerciseService.getAll());
        return "admin/program/edit-program";
    }

    /**
     * Edit program part.
     *
     * @param id the id
     * @param order the order
     * @param exerciseId the exercise id
     * @param segmentType the segment type
     * @param setsNumber the sets number
     * @param setRestTime the set rest time
     * @param repetitions the repetitions
     * @param elementRestTime the element rest time
     * @param model the model
     * @return edit program page
     */
    @SneakyThrows
    @PostMapping("/admin/programsegment/edit/{id}")
    public String editProgramPart(@PathVariable Long id,
                             @RequestParam Integer order,
                             @RequestParam("exercise") Long exerciseId,
                             @RequestParam("segment-type") String segmentType,
                             @RequestParam("sets") Integer setsNumber,
                             @RequestParam("rest-time") Integer setRestTime,
                             @RequestParam("repetitions") Integer repetitions,
                             @RequestParam("element-rest-time") Integer elementRestTime,
                             Model model) {
        ProgramSegment programSegment = programSegmentService.get(id);
        programSegment.setOrder(order);
        programSegment.setExercise(exerciseService.get(exerciseId));
        programSegment.setProgramSegmentType(segmentType);
        programSegment.setSetsNumber(setsNumber);
        programSegment.setRestTimeSeconds(setRestTime);
        programSegment.setRepetitions(repetitions);
        programSegment.setElementRestTimeSeconds(elementRestTime);
        programSegmentService.save(programSegment);
        model.addAttribute("activeMenu", "side-menu-del-program");
        model.addAttribute("program", programSegment.getProgram());
        model.addAttribute("difficulties", difficultyService.getAll());
        model.addAttribute("exercises", exerciseService.getAll());
        return "admin/program/edit-program";
    }


    /**
     * Delete program.
     *
     * @param id the id
     * @param model the model
     * @return program list page
     */
    @PostMapping("/admin/program/delete/{id}")
    public String deleteProgram(@PathVariable Long id, Model model) {
        programService.delete(id);
        model.addAttribute("activeMenu", "side-menu-del-program");
        model.addAttribute("programs", programService.getAll());
        return "admin/program/list-programs";
    }

    /**
     * Delete program part.
     *
     * @param id the id
     * @param model the model
     * @return edit program page
     */
    @PostMapping("/admin/programsegment/delete/{id}")
    public String deleteProgramPart(@PathVariable Long id, Model model) {
        ProgramSegment programSegment = programSegmentService.get(id);
        programSegmentService.delete(programSegment);
        model.addAttribute("activeMenu", "side-menu-del-program");
        model.addAttribute("program", programSegment.getProgram());
        model.addAttribute("difficulties", difficultyService.getAll());
        model.addAttribute("exercises", exerciseService.getAll());
        return "admin/program/edit-program";
    }

    /**
     * Add program page.
     *
     * @param model the model
     * @return add program page
     */
    @GetMapping("/admin/program/add")
    public String addProgramPage(Model model) {
        model.addAttribute("activeMenu", "side-menu-add-program");
        model.addAttribute("difficulties", difficultyService.getAll());
        return "admin/program/add-program";
    }

    /**
     * Add program.
     *
     * @param name the name
     * @param imageFile the image file
     * @param description the description
     * @param difficultyId the difficulty id
     * @param restTime the rest time
     * @param model the model
     * @return edit program page
     */
    @SneakyThrows
    @PostMapping("/admin/program/add")
    public String addProgram(@RequestParam String name,
                            @RequestParam("newImage") MultipartFile imageFile,
                            @RequestParam String description,
                            @RequestParam("difficulty") Long difficultyId,
                            @RequestParam("rest-time") Integer restTime,
                            Model model) {
        Program program = new Program();
        program.setName(name);
        Image image = new Image();
        image.setImage(imageFile.getBytes());
        Image savedImage = imageService.save(image);
        program.setImage(savedImage);
        program.setDescription(description);
        program.setDifficulty(difficultyService.get(difficultyId));
        program.setRestTimeSeconds(restTime);
        model.addAttribute("activeMenu", "side-menu-del-program");
        model.addAttribute("program", programService.save(program));
        model.addAttribute("difficulties", difficultyService.getAll());
        model.addAttribute("exercises", exerciseService.getAll());
        return "admin/program/edit-program";
    }

    /**
     * Add program segment.
     *
     * @param programId the program id
     * @param order the order
     * @param exerciseId the exercise id
     * @param segmentType the segment type
     * @param setsNumber the sets number
     * @param setRestTime the set rest time
     * @param repetitions the repetitions
     * @param elementRestTime the element rest time
     * @param model the model
     * @return edit program page
     */
    @SneakyThrows
    @PostMapping("/admin/programsegment/add/{id}")
    public String addProgram(@PathVariable("id") Long programId,
                            @RequestParam Integer order,
                            @RequestParam("exercise") Integer exerciseId,
                            @RequestParam("segment-type") String segmentType,
                            @RequestParam("sets") Integer setsNumber,
                            @RequestParam("rest-time") Integer setRestTime,
                            @RequestParam("repetitions") Integer repetitions,
                            @RequestParam("element-rest-time") Integer elementRestTime,
                            Model model) {
        ProgramSegment programSegment = new ProgramSegment();
        programSegment.setProgram(programService.get(programId));
        programSegment.setOrder(order);
        programSegment.setExercise(exerciseService.get(exerciseId));
        programSegment.setProgramSegmentType(segmentType);
        programSegment.setSetsNumber(setsNumber);
        programSegment.setRestTimeSeconds(setRestTime);
        programSegment.setRepetitions(repetitions);
        programSegment.setElementRestTimeSeconds(elementRestTime);
        customNativeRepository.insertProgramSegment(programSegment);
        model.addAttribute("activeMenu", "side-menu-del-program");
        model.addAttribute("program", programSegment.getProgram());
        model.addAttribute("difficulties", difficultyService.getAll());
        model.addAttribute("exercises", exerciseService.getAll());
        return "admin/program/edit-program";
    }
}
