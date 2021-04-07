package by.gstu.workout.service;

import by.gstu.workout.model.MuscleGroup;
import by.gstu.workout.repository.MuscleGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuscleGroupService {

    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    public List<MuscleGroup> getAll(){
        return muscleGroupRepository.findAll();
    }
}
