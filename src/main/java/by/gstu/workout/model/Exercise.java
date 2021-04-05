package by.gstu.workout.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercise", catalog = "fitness")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @JoinColumn(name = "muscle_group_id")
    @ManyToOne
    private MuscleGroup muscleGroup;
    @Column(name = "image_id")
    private Long imageId;
    @JoinColumn(name = "equipment_id")
    @ManyToOne
    private Equipment equipment;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exercise")//mappedBy - имя переменной в другой таблице
    private List<ExercisePart> exerciseParts;
}

