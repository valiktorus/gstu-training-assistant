package by.gstu.workout.service;

import by.gstu.workout.model.Book;
import by.gstu.workout.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements GenericLibraryService<Book> {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> search(String... searchStrings) {
        return null;
    }

    @Override
    public Book get(long id) {
        return bookRepository.getOne(id);
    }

    @Override
    public Book save(Book obj) {
        return bookRepository.save(obj);
    }

    @Override
    public void delete(Book object) {
        bookRepository.delete(object);
    }

    @Override
    public List<Book> getAll(Sort sort) {
        return bookRepository.findAll(sort);
    }

    @Override
    public Page<Book> getAll(int pageNumber, int pageSize, String sortedField, Sort.Direction direction) {
        return bookRepository.findAllBy(PageRequest.of(pageNumber, pageSize, direction, sortedField));
    }

    @Override
    public Page<Book> search(int pageNumber, int pageSize, String sortedField, Sort.Direction direction, String... searchObjects) {
        return bookRepository.findAll(PageRequest.of(pageNumber, pageSize, direction, searchObjects));
    }

    public Page<Book> getAllByGenre(String genre, int pageNumber, int pageSize, String sortedField, Sort.Direction direction) {
        return bookRepository.findAllByGenreGenreEquals(genre, PageRequest.of(pageNumber, pageSize, direction, sortedField));
    }

    public Page<Book> getAllByGenreAndAuthor(String genre, String author, int pageNumber,
                                             int pageSize, String sortedField, Sort.Direction direction) {
        return bookRepository.findAllByGenreGenreContainingAndAuthorFullNameContaining(genre, author,
                PageRequest.of(pageNumber, pageSize, direction, sortedField));
    }
    public Page<Book> getAllByAuthor(String author, int pageNumber,
                                             int pageSize, String sortedField, Sort.Direction direction) {
        return bookRepository.findAllByAuthorFullNameContaining(author,
                PageRequest.of(pageNumber, pageSize, direction, sortedField));
    }
}
