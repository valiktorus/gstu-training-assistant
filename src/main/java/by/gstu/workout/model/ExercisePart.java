package by.gstu.workout.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(name = "exercise_part", catalog = "fitness")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExercisePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int order;
    private String description;
    @JoinColumn(name = "image_id")
    @OneToOne
    private Image image;
    @JoinColumn(name = "exercise_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Exercise exercise;
}
