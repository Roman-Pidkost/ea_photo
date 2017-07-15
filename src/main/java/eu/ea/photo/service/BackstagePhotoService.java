package eu.ea.photo.service;

import eu.ea.photo.entity.BackstagePhoto;
import eu.ea.photo.repository.BackstagePhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackstagePhotoService {

    @Autowired
    private BackstagePhotoRepository backstagePhotoRepository;

    public BackstagePhoto save(BackstagePhoto backstagePhoto) {
        return backstagePhotoRepository.save(backstagePhoto);
    }

    public void delete(Integer id) {
        backstagePhotoRepository.delete(id);
    }
}
