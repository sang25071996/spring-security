package sang.uaa.com.vn.mongodb.service;

import sang.uaa.com.vn.mongodb.Category;

import java.util.List;

public interface ICategroyService {

    Category create(Category category);

    List<Category> findCategorys();

    List<Category> findCategorysByName(String name);

    List<Category> findCategorysLikeName(String name);
}
