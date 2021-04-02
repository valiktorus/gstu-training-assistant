package by.gstu.workout.model;

import by.gstu.workout.enums.ProgramSegmentTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(name = "program_segment_type",catalog = "fitness")
@Data
@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class ProgramSegmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProgramSegmentTypeEnum name;

}
