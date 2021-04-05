package by.gstu.workout.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(name = "images", catalog = "fitness")
@Data
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@SelectBeforeUpdate
@NoArgsConstructor
public class Image {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private byte[] image;

    public Image(Long id) {
        this.id = id;
    }
}
