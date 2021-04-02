package by.gstu.workout.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "program", catalog = "fitness")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Program {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String description;
    @Column(name = "rest_time_seconds")
    private int restTimeSeconds;
    @JoinColumn(name = "image_id")
    @OneToOne
    private Image image;
    @JoinColumn(name = "difficulty_id")
    @ManyToOne
    private Difficulty difficulty;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "program")
    private List<ProgramSegment> programSegments;
}
