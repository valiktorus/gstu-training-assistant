package by.gstu.workout.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(name = "program_segment", catalog = "fitness")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@AllArgsConstructor
@NoArgsConstructor
public class ProgramSegment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private int order;
    private int repetitions;
    @Column(name = "sets_number")
    private int setsNumber;
    @Column(name = "rest_time_seconds")
    private int restTimeSeconds;
    @JoinColumn(name = "exercise_id")
    @ManyToOne
    private Exercise exercise;
    @JoinColumn(name = "program_id")
    @ManyToOne
    private Program program;
    @JoinColumn(name = "type_id")
    @ManyToOne
    private ProgramSegmentType programSegmentType;
}
