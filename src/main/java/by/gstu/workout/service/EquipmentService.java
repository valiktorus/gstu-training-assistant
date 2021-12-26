package by.gstu.workout.service;

import by.gstu.workout.model.Equipment;
import by.gstu.workout.model.Image;
import by.gstu.workout.repository.EquipmentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Equipment service.
 */
@Service
public class EquipmentService {

    @Autowired
    private ImageService imageService;

    @Autowired
    private EquipmentRepository equipmentRepository;

    /**
     * Gets all equipments.
     *
     * @return all equipments
     */
    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }

    /**
     * Get equipment.
     *
     * @param id the id
     * @return the equipment
     */
    public Equipment get(Long id){
        return equipmentRepository.getOne(id);
    }

    /**
     * Update equipment.
     *
     * @param equipment the equipment
     * @return the equipment
     */
    public Equipment update(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    /**
     * Delete equipment.
     *
     * @param equipment the equipment
     */
    public void delete(Equipment equipment){
         equipmentRepository.delete(equipment);
    }

    /**
     * Save equipment.
     *
     * @param name the name
     * @param imageFile the image file
     * @return the equipment
     */
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
