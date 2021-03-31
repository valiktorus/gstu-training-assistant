package by.gstu.workout.repository;

import by.gstu.workout.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("select new Book(book.id, book.image) from Book book")
    List<Book> findTopBy(Pageable pageable);

    @Query("select new Book(book.id, book.bookName, book.image.id, book.author, book.genre, book.publicationDate, book.annotation) " +
            "from Book book")
    Page<Book> findAllBy(Pageable pageable);

    Page<Book> findAllByGenreGenreEquals(String genre, Pageable pageable);

    Page<Book> findAllByGenreGenreEqualsAndAuthorFullNameContaining(String genre, String fullName, Pageable pageable);

    Page<Book> findAllByGenreGenreContainingAndAuthorFullNameContaining(String genre, String fullName, Pageable pageable);

    Page<Book> findAllByAuthorFullNameContaining(String fullName, Pageable pageable);

    Page<Book> findAllByAuthorFullNameContaining(Pageable pageable,String... fullName);



}
