package by.gstu.workout.service;

import by.gstu.workout.model.Author;
import by.gstu.workout.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService implements GenericLibraryService<Author>{
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> search(String... searchStrings) {
        List<Author> authors = new ArrayList<>();
        for (String searchString:searchStrings) {
            authors.addAll(authorRepository.findAllByFullNameContaining(searchString));
        }
        return authors;
    }

    @Override
    public Author get(long id) {
        return authorRepository.getOne(id);
    }

    @Override
    public Author save(Author obj) {
        return authorRepository.save(obj);
    }

    @Override
    public void delete(Author object) {
        authorRepository.delete(object);
    }

    @Override
    public List<Author> getAll(Sort sort) {
        return authorRepository.findAll(sort);
    }

    @Override
    public Page<Author> getAll(int pageNumber, int pageSize, String sortedField, Sort.Direction direction) {
        return authorRepository.findAll(PageRequest.of(pageNumber, pageSize, direction));
    }

    @Override
    public Page<Author> search(int pageNumber, int pageSize, String sortedField, Sort.Direction direction, String... searchObjects) {
        return authorRepository.findAll(PageRequest.of(pageNumber, pageSize, direction, searchObjects));
    }
}
