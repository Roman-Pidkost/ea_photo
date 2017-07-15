package eu.ea.photo.service;

import eu.ea.photo.entity.Backstage;
import eu.ea.photo.repository.BackstageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackstageService {

    @Autowired
    private BackstageRepository backstageRepository;

    public Backstage save(Backstage backstage) {
        return backstageRepository.save(backstage);
    }

    public List<Backstage> findAll() {
        return backstageRepository.findAll(sortByPriorityASC());
    }

    public Backstage findOne(Integer id) {
        return backstageRepository.findOne(id);
    }

    public Backstage findOne(String name) {
        return backstageRepository.findByName(name);
    }

    public void delete(Integer id) {
        backstageRepository.delete(id);
    }

    private Sort sortByPriorityASC() {
        return new Sort(Sort.Direction.ASC, "priority");
    }
}
