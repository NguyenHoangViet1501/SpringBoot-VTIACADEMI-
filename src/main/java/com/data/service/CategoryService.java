package com.data.service;

import com.data.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    boolean create(Category category);

    boolean update(Category category);

    boolean delete(int id);

    boolean existCategory(int id);

    Category getById(int id);
}
