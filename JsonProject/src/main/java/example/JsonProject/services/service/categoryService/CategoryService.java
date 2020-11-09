package example.JsonProject.services.service.categoryService;

import example.JsonProject.models.Category;
import example.JsonProject.services.seed.CategorySeed;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void createCategory(CategorySeed categorySeed);
    List<Category> getRandomCategory();
}
