package by.gstu.workout.service;

import by.gstu.workout.model.Image;
import by.gstu.workout.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements GenericLibraryService<Image> {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    @Override
    public List<Image> search(String... searchStrings) {
        return null;
    }

    @Override
    public Image get(long id) {
        return imageRepository.getOne(id);
    }

    @Override
    public Image save(Image obj) {
        return imageRepository.save(obj);
    }

    @Override
    public void delete(Image object) {
        imageRepository.delete(object);
    }

    @Override
    public List<Image> getAll(Sort sort) {
        return imageRepository.findAll(sort);
    }

    @Override
    public Page<Image> getAll(int pageNumber, int pageSize, String sortedField, Sort.Direction direction) {
        return imageRepository.findAll(PageRequest.of(pageNumber, pageSize, direction));
    }

    @Override
    public Page<Image> search(int pageNumber, int pageSize, String sortedField, Sort.Direction direction, String... searchObjects) {
        return imageRepository.findAll(PageRequest.of(pageNumber, pageSize, direction, searchObjects));
    }
}
