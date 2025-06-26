package com.data.service;

import com.data.entity.Category;
import com.data.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceIpml implements CategoryService{

    private CategoryRepository categoryRepository;

    public CategoryServiceIpml(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean create(Category category) {
        if(categoryRepository.existsByCategoryName(category.getCategoryName())){
            return false;
        }
        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean update(Category category) {
        if(!categoryRepository.existsById(category.getId())){
            return false;
        }
        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean delete(int id) {
        if(!categoryRepository.existsById(id)){
            return false;

        }
        categoryRepository.deleteById(id);
        return true;
    }
}
