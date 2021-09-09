package by.gstu.workout.model;

import by.gstu.workout.service.ProgramSegmentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(name = "program_segment", catalog = "fitness")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Getter
@Setter
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
    @Column(name = "element_time_seconds")
    private int elementRestTimeSeconds;
    @JoinColumn(name = "exercise_id")
    @ManyToOne
    private Exercise exercise;
    @JoinColumn(name = "program_id")
    @ManyToOne
    private Program program;
    @Column(name = "type_id")
    private String programSegmentType;
}
