package by.gstu.workout.service;

import by.gstu.workout.model.Image;
import by.gstu.workout.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Image service.
 */
@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    /**
     * Get by id image.
     *
     * @param id the id
     * @return the image
     */
    public Image getById(Long id){
        return imageRepository.findById(id).orElse(new Image());
    }

    /**
     * Save image.
     *
     * @param image the image
     * @return the image
     */
    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
