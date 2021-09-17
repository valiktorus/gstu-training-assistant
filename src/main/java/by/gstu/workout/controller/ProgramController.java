package by.gstu.workout.controller;

import by.gstu.workout.model.Program;
import by.gstu.workout.model.ProgramSegment;
import by.gstu.workout.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;

@Controller
public class ProgramController {
    @Autowired
    private ProgramService programService;
    @GetMapping("/program/{id}")
    public String getSingleProgramPage(@PathVariable Long id, Model model, OAuth2AuthenticationToken token){
        Program program = programService.get(id);
        program.getProgramSegments().sort(Comparator.comparing(ProgramSegment::getOrder));
        model.addAttribute("program", programService.get(id));
        model.addAttribute("userName", ((DefaultOidcUser) token.getPrincipal()).getGivenName());
        return "single-program";
    }
}
