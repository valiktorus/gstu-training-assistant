package by.gstu.workout.model;

import by.gstu.workout.enums.DifficultyName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(name = "difficulty", catalog = "fitness")
@Data
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@NoArgsConstructor
public class Difficulty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
//    @Enumerated(EnumType.STRING)
    private String name;
}
