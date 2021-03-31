package by.gstu.workout.model;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books", catalog = "library")
@Data
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "book_name")
    private String bookName;
    @ManyToOne
    @JoinColumn
    private Image image;
    @ManyToOne
    @JoinColumn
    private Author author;
    @ManyToOne
    @JoinColumn
    private Genre genre;
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    private String annotation;


    public Book(Long id, Image image) {
        this.id = id;
        this.image = image;
    }

    public Book(Long id, Long imageId) {
        this.id = id;
        this.image = new Image(imageId);
    }

    public Book(Long id, String bookName, Long imageId, Author author, Genre genre, LocalDate publicationDate, String annotation) {
        this.id = id;
        this.bookName = bookName;
        this.image = new Image(imageId);
        this.author = author;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", publicationDate=" + publicationDate +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
