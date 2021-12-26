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

/**
 *Admin equipment controller.
 */
@Controller
public class AdminEquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    /**
     * Gets delete update equipment page.
     *
     * @param model the model
     * @return the delete update equipment page
     */
    @GetMapping("/admin/delete/equipment")
    public String getDeleteUpdateEquipmentPage(Model model) {
        model.addAttribute("activeMenu", "side-menu-del-eq");
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/equipment/delete-or-update-equipment";
    }

    /**
     * Delete equipment.
     *
     * @param id the id
     * @param model the model
     * @return delete update equipment page
     */
    @PostMapping("/admin/delete/equipment/{id}")
    public String deleteEquipment(@PathVariable Long id, Model model) {
        equipmentService.delete(equipmentService.get(id));
        model.addAttribute("activeMenu", "side-menu-del-eq");
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/equipment/delete-or-update-equipment";
    }

    /**
     * Gets update equipment page.
     *
     * @param id the id
     * @param model the model
     * @return the update equipment page
     */
    @GetMapping("/admin/update/equipment/{id}")
    public String getUpdateEquipmentPage(@PathVariable Long id, Model model) {
        model.addAttribute("activeMenu", "side-menu-del-eq");
        model.addAttribute("equipment", equipmentService.get(id));
        return "admin/equipment/update-equipment";
    }

    /**
     * Update equipment.
     *
     * @param id the id
     * @param image the image
     * @param name the name
     * @param model the model
     * @return update equipment page
     */
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

    /**
     * forward to Update equipment page.
     *
     * @param model the model
     * @return Update equipment page
     */
    @SneakyThrows
    @GetMapping("/admin/add/equipment")
    public String updateEquipment(Model model) {
        model.addAttribute("activeMenu", "side-menu-add-eq");
        model.addAttribute("equipments", equipmentService.getAll());
        return "admin/equipment/add-equipment";
    }

    /**
     * Update equipment.
     *
     * @param image the image
     * @param name the name
     * @param model the model
     * @return list equipment page
     */
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
