package by.gstu.workout.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        model.addAttribute("activeMenu", "side-menu-general");
        return "admin-main";
    }
}
