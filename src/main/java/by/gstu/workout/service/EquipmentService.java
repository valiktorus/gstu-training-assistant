package by.gstu.workout.service;

import by.gstu.workout.model.Equipment;
import by.gstu.workout.model.Image;
import by.gstu.workout.repository.EquipmentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private ImageService imageService;

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }

    public Equipment get(Long id){
        return equipmentRepository.getOne(id);
    }

    public Equipment update(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public void delete(Equipment equipment){
         equipmentRepository.delete(equipment);
    }

    @SneakyThrows
    public Equipment save(String name, MultipartFile imageFile) {
        Image image = new Image();
        image.setImage(imageFile.getBytes());
        Image savedImage = imageService.save(image);
        Equipment equipment = new Equipment();
        equipment.setImage(savedImage);
        equipment.setName(name);
        return equipmentRepository.save(equipment);
    }
}
