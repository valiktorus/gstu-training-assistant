package by.gstu.workout.service;

import by.gstu.workout.model.ProgramSegment;
import by.gstu.workout.repository.ProgramSegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramSegmentService {
    @Autowired
    private ProgramSegmentRepository programSegmentRepository;

    public ProgramSegment get(Long id){
        return programSegmentRepository.getOne(id);
    }

    public List<ProgramSegment> getAll(){
        return programSegmentRepository.findAll();
    }

    public void delete(ProgramSegment programSegment){
        programSegmentRepository.delete(programSegment);
    }

    public ProgramSegment save(ProgramSegment programSegment){
        return  programSegmentRepository.save(programSegment);
    }
}
