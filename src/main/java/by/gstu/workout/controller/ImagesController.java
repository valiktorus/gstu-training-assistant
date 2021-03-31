package by.gstu.workout.controller;

import by.gstu.workout.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ImagesController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/image/{imageId}")
    @ResponseBody
    public void greeting(@PathVariable(name = "imageId") Long imageId, HttpServletResponse response, Model model)
            throws IOException {
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(imageService.get(imageId).getImage());
        response.getOutputStream().close();
    }
}
