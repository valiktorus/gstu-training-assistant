package by.gstu.workout.service;

import by.gstu.workout.model.Equipment;
import by.gstu.workout.model.Image;
import by.gstu.workout.model.MuscleGroup;
import by.gstu.workout.repository.MuscleGroupRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MuscleGroupService {

    @Autowired
    private MuscleGroupRepository muscleGroupRepository;
    @Autowired
    private ImageService imageService;

    public List<MuscleGroup> getAll() {
        return muscleGroupRepository.findAll();
    }

    public MuscleGroup get(Long id) {
        return muscleGroupRepository.getOne(id);
    }

    @SneakyThrows
    public MuscleGroup save(String name, MultipartFile imageFile) {
        Image image = new Image();
        image.setImage(imageFile.getBytes());
        Image savedImage = imageService.save(image);
        MuscleGroup muscleGroup = new MuscleGroup();
        muscleGroup.setImage(savedImage);
        muscleGroup.setName(name);
        return muscleGroupRepository.save(muscleGroup);
    }

    public MuscleGroup save(MuscleGroup muscleGroup) {
        return muscleGroupRepository.save(muscleGroup);
    }

    public void delete(MuscleGroup muscleGroup){
        muscleGroupRepository.delete(muscleGroup);
    }


}
