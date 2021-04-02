package by.gstu.workout.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @JoinColumn(name = "image_id")
    @OneToOne
    private Image image;
    @JoinColumn(name = "equipment_id")
    @ManyToOne
    private Equipment equipment;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exercise")//mappedBy - имя переменной в другой таблице
    private List<ExercisePart> exerciseParts;
}

