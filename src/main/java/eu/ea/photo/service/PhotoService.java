package eu.ea.photo.service;

import eu.ea.photo.entity.Photo;
import eu.ea.photo.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    public List<Photo> findAll() {
        return photoRepository.findAll(sortByPriorityASC());
    }

    public Photo findOne(Integer id) {
        return photoRepository.findOne(id);
    }

    public void delete(Integer id) {
        photoRepository.delete(id);
    }

    private Sort sortByPriorityASC() {
        return new Sort(Sort.Direction.ASC, "priority");
    }
}
