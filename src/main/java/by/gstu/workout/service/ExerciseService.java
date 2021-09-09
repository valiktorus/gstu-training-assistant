package by.gstu.workout.service;

import by.gstu.workout.Constants;
import by.gstu.workout.model.Exercise;
import by.gstu.workout.model.Image;
import by.gstu.workout.repository.ExerciseRepository;
import by.gstu.workout.service.sorting.SortingService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class ExerciseService {

    @Autowired
    private  ExerciseRepository exerciseRepository;
    @Autowired
    private MuscleGroupService muscleGroupService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private SortingService sortingService;
    @Autowired
    private ImageService imageService;

    public Exercise get(long id) {
        return exerciseRepository.findById(id).orElseThrow(()-> new RuntimeException("no such exercise"));
    }
    public List<Exercise> getAll(){
        return exerciseRepository.findAll();
    }

    @SneakyThrows
    public Exercise save(String name, MultipartFile imageFile, Long muscleGroupId, Long equipmentId, String description) {
        Exercise exercise = new Exercise();
        Image image = new Image();
        image.setImage(imageFile.getBytes());
        Image savedImage = imageService.save(image);
        exercise.setImageId(savedImage.getId());
        exercise.setEquipment(equipmentService.get(equipmentId));
        exercise.setMuscleGroup(muscleGroupService.get(muscleGroupId));
        exercise.setName(name);
        exercise.setDescription(description);
        return save(exercise);

    }
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public void delete(Exercise exercise) {
        exerciseRepository.delete(exercise);
    }

    public Page<Exercise> getAllByMuscleGroupAndEquipment(String muscleGroup, String equipment, int pageNumber,
                                             int pageSize, String sortedField, Sort.Direction direction) {
        String currentMuscleGroup = Constants.DEFAULT_FILTRATION_VALUE.equals(muscleGroup) ? null : muscleGroup;
        String currentEquipment = Constants.DEFAULT_FILTRATION_VALUE.equals(equipment) ? null : equipment;
        String currentSortedBy = Constants.DEFAULT_SORTING_VALUE.equals(sortedField) ? "id" :
                sortingService.getConvertedExerciseSortingField(sortedField);
        Pageable pageRequest = PageRequest.of(pageNumber - 1, pageSize, direction, currentSortedBy);
        if (Objects.isNull(currentMuscleGroup) && Objects.isNull(currentEquipment)) {
            return exerciseRepository.findAll(pageRequest);
        }
        if (Objects.isNull(currentMuscleGroup)){
            return exerciseRepository.findAllByEquipmentName(currentEquipment, pageRequest);
        }
        if (Objects.isNull(currentEquipment)) {
            return exerciseRepository.findAllByMuscleGroupName(currentMuscleGroup, pageRequest);
        }
        return exerciseRepository.findAllByMuscleGroupNameAndEquipmentName(muscleGroup, equipment,
                pageRequest);
    }
}
