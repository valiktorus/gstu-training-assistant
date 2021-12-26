package by.gstu.workout.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Admin controller.
 */
@Controller
public class AdminController {

    /**
     * Get admin panel page.
     *
     * @param model the model
     * @return the admin page
     */
    @GetMapping("/admin")
    public String getAdminPage(Model model){
        model.addAttribute("activeMenu", "side-menu-general");
        return "admin-main";
    }
}
