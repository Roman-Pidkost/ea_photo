package eu.ea.photo.repository;

import eu.ea.photo.entity.Backstage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackstageRepository extends JpaRepository<Backstage, Integer> {
    Backstage findByName(String name);
}
