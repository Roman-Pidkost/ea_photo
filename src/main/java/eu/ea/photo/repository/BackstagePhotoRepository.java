package eu.ea.photo.repository;

import eu.ea.photo.entity.BackstagePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackstagePhotoRepository extends JpaRepository<BackstagePhoto, Integer> {
}
