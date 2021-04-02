package by.gstu.workout.model;

import by.gstu.workout.enums.DifficultyName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity//сущность в JPA
@Table(name = "difficulty", catalog = "fitness") //table and database
@Data//get set allArgConstructor toString equals hashcode
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@NoArgsConstructor
public class Difficulty {
    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//сгенерировать используя сологку в базе данных
    private Long id;
    @Column(name = "name")//в столбце name
    @Enumerated(EnumType.STRING)//сравнение по строке
    private DifficultyName difficultyName;
}
