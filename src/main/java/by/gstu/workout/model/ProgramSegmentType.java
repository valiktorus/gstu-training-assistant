package by.gstu.workout.model;

import by.gstu.workout.enums.ProgramSegmentTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(name = "program_segment_type",catalog = "fitness")
@Data
@SelectBeforeUpdate
@Getter
@Setter
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
