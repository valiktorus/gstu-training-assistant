package by.gstu.workout.service;

import by.gstu.workout.model.Image;
import by.gstu.workout.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    public Image getById(Long id){
        return imageRepository.findById(id).orElse(new Image());
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
