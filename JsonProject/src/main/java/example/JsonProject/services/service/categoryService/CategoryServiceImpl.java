package example.JsonProject.services.service.categoryService;


import example.JsonProject.models.Category;
import example.JsonProject.repositories.CategoryRepository;
import example.JsonProject.services.seed.CategorySeed;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCategory(CategorySeed categorySeed) {
        this.categoryRepository.saveAndFlush(this.modelMapper.map(categorySeed, Category.class));
    }

    @Override
    public List<Category> getRandomCategory() {
        Random random = new Random();

        int categoriesNumber = random.nextInt(2) + 1;
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < categoriesNumber; i++) {
            long randomId = random.nextInt((int) this.categoryRepository.count()) + 1;
            Category one = this.categoryRepository.findById(randomId);


            categories.add(one);
        }

        return categories;
    }
}
