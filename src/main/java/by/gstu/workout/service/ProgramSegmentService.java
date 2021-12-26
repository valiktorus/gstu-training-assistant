package by.gstu.workout.service;

import by.gstu.workout.model.ProgramSegment;
import by.gstu.workout.repository.ProgramSegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Program segment service.
 */
@Service
public class ProgramSegmentService {
    @Autowired
    private ProgramSegmentRepository programSegmentRepository;

    /**
     * Get program segment.
     *
     * @param id the id
     * @return the program segment
     */
    public ProgramSegment get(Long id){
        return programSegmentRepository.getOne(id);
    }

    /**
     * Get list of program segments.
     *
     * @return  list of program segments
     */
    public List<ProgramSegment> getAll(){
        return programSegmentRepository.findAll();
    }

    /**
     * Delete program segment.
     *
     * @param programSegment the program segment
     */
    public void delete(ProgramSegment programSegment){
        programSegmentRepository.delete(programSegment);
    }

    /**
     * Save program segment.
     *
     * @param programSegment the program segment
     * @return the program segment
     */
    public ProgramSegment save(ProgramSegment programSegment){
        return  programSegmentRepository.save(programSegment);
    }
}
