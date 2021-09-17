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

    @GetMapping("/admin/program/list")
    public String listProgramPage(Model model) {
        model.addAttribute("activeMenu", "side-menu-del-program");
        model.addAttribute("programs", programService.getAll());
        return "admin/program/list-programs";
    }

    @GetMapping("/admin/program/edit/{id}")
    public String editProgramPage(@PathVariable Long id, Model model) {
        model.addAttribute("activeMenu", "side-menu-del-program");
        model.addAttribute("program", programService.get(id));
        model.addAttribute("difficulties", difficultyService.getAll());
        model.addAttribute("exercises", exerciseService.getAll());
        return "admin/program/edit-program";
    }

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


    @PostMapping("/admin/program/delete/{id}")
    public String deleteProgram(@PathVariable Long id, Model model) {
        programService.delete(id);
        model.addAttribute("activeMenu", "side-menu-del-program");
        model.addAttribute("programs", programService.getAll());
        return "admin/program/list-programs";
    }

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

    @GetMapping("/admin/program/add")
    public String addProgramPage(Model model) {
        model.addAttribute("activeMenu", "side-menu-add-program");
        model.addAttribute("difficulties", difficultyService.getAll());
        return "admin/program/add-program";
    }

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
