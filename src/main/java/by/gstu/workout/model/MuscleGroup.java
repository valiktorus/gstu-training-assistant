package by.gstu.workout.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "muscle_group", catalog = "fitness")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@AllArgsConstructor
@NoArgsConstructor
public class MuscleGroup {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    @JoinColumn(name = "image_id")
    @ManyToOne
    private Image image;
}
