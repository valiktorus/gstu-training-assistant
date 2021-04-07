package by.gstu.workout.service;

import by.gstu.workout.model.Equipment;
import by.gstu.workout.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }
}
