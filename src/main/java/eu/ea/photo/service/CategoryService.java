package eu.ea.photo.service;

import eu.ea.photo.entity.Category;
import eu.ea.photo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll(sortByPriorityASC());
    }

    public Category findOne(Integer id) {
        return categoryRepository.findOne(id);
    }

    public void delete(Integer id) {
        categoryRepository.delete(id);
    }

    private Sort sortByPriorityASC() {
        return new Sort(Sort.Direction.ASC, "priority");
    }
}
