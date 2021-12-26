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

/**
 * Image controller.
 */
@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;

    /**
     * Get image. write to outputStream image
     *
     * @param imageId the image id
     * @param response the response
     * @param model the model
     * @throws IOException the io exception
     */
    @GetMapping("/image/{imageId}")
    @ResponseBody
    public void getImage(@PathVariable(name = "imageId") Long imageId, HttpServletResponse response, Model model)
            throws IOException {
        response.getOutputStream().write(imageService.getById(imageId).getImage());
        response.getOutputStream().close();
    }
}
