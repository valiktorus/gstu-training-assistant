package by.gstu.workout.controller.admin;

import by.gstu.workout.model.Equipment;
import by.gstu.workout.model.Image;
import by.gstu.workout.service.EquipmentService;
import com.sun.xml.bind.v2.TODO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminEquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/admin/delete/equipment")
    public String getDeleteUpdateEquipmentPage(Model model) {
        model.addAttribute("activeMenu", "side-menu-del-eq");
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/equipment/delete-or-update-equipment";
    }

    @PostMapping("/admin/delete/equipment/{id}")
    public String deleteEquipment(@PathVariable Long id, Model model) {
        equipmentService.delete(equipmentService.get(id));
        model.addAttribute("activeMenu", "side-menu-del-eq");
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/equipment/delete-or-update-equipment";
    }

    @GetMapping("/admin/update/equipment/{id}")
    public String getUpdateEquipmentPage(@PathVariable Long id, Model model) {
        model.addAttribute("activeMenu", "side-menu-del-eq");
        model.addAttribute("equipment", equipmentService.get(id));
        return "admin/equipment/update-equipment";
    }

    @SneakyThrows
    @PostMapping("/admin/update/equipment/{id}")
    public String updateEquipment(@PathVariable Long id,
                                  @RequestParam("newImage") MultipartFile image,
                                  @RequestParam String name,
                                  Model model) {
        Equipment previousEquipment = equipmentService.get(id);
        if (!image.isEmpty()) {
            previousEquipment.getImage().setImage(image.getBytes());
        }
        previousEquipment.setName(name);
        model.addAttribute("activeMenu", "side-menu-del-eq");
        model.addAttribute("equipment",equipmentService.update(previousEquipment));
        return "admin/equipment/update-equipment";
    }

    @SneakyThrows
    @GetMapping("/admin/add/equipment")
    public String updateEquipment(Model model) {
        model.addAttribute("activeMenu", "side-menu-add-eq");
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/equipment/add-equipment";
    }

    @SneakyThrows
    @PostMapping("/admin/add/equipment")
    public String updateEquipment(@RequestParam("newImage") MultipartFile image,
                                  @RequestParam String name,
                                  Model model) {
        equipmentService.save(name, image);
        model.addAttribute("activeMenu", "side-menu-add-eq");
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/equipment/delete-or-update-equipment";
    }

}
