package by.gstu.workout.service;

import by.gstu.workout.model.Genre;
import by.gstu.workout.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements GenericLibraryService<Genre> {
    @Autowired
    private GenreRepository genreRepository;
    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> search(String... searchStrings) {
        return null;
    }

    @Override
    public Genre get(long id) {
        return genreRepository.getOne(id);
    }

    @Override
    public Genre save(Genre obj) {
        return genreRepository.save(obj);
    }

    @Override
    public void delete(Genre object) {
        genreRepository.delete(object);
    }

    @Override
    public List<Genre> getAll(Sort sort) {
        return genreRepository.findAll(sort);
    }

    @Override
    public Page<Genre> getAll(int pageNumber, int pageSize, String sortedField, Sort.Direction direction) {
        return genreRepository.findAll(PageRequest.of(pageNumber, pageSize, direction));
    }

    @Override
    public Page<Genre> search(int pageNumber, int pageSize, String sortedField, Sort.Direction direction, String... searchObjects) {
        return genreRepository.findAll(PageRequest.of(pageNumber, pageSize, direction, searchObjects));
    }
}
