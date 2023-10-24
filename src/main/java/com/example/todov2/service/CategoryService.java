package com.example.todov2.service;

import com.example.todov2.entity.Category;
import com.example.todov2.repository.CategoryRepository;
import com.example.todov2.search.CategorySearchValues;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(long id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Category findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(long id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> search(CategorySearchValues searchValues) {
        System.out.println("aaa");
        System.out.println("ghfhf");
        System.out.println("aaa");
        System.out.println("aaa");
        System.out.println("апывп");
        System.out.println("апывп");
        System.out.println("апывп");
        return categoryRepository.findByTitleAll(searchValues.getText());
    }
}
