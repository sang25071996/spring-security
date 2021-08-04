package sang.uaa.com.vn.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sang.uaa.com.vn.mongodb.Category;
import sang.uaa.com.vn.mongodb.repository.CategoryRepository;

import java.util.List;

@Service
public class CategroyService implements ICategroyService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        this.categoryRepository.save(category);
        return category;
    }

    @Override
    public List<Category> findCategorys() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<Category> findCategorysByName(String name) {
        return this.categoryRepository.findCategorysByName(name);
    }

    @Override
    public List<Category> findCategorysLikeName(String name) {
        return this.categoryRepository.findCategorysLikeName(parseArgsLike(name));
    }

    private String parseArgsLike(String field) {
        StringBuilder builder = new StringBuilder();
        builder.append(".*");
        builder.append(field);
        builder.append(".*");
        return builder.toString();
    }
}
