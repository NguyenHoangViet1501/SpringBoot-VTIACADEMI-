package com.data.controller;

import com.data.dto.CategoryCreateDTO;
import com.data.dto.CategoryDTO;
import com.data.entity.Category;
import com.data.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Category> categories = categoryService.getAll();

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        categories.forEach(category -> {
            CategoryDTO categoryDTO = new CategoryDTO();

            categoryDTO.setId(category.getId());
            categoryDTO.setCategoryName(category.getCategoryName());
            categoryDTOS.add(categoryDTO);
        });

        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryCreateDTO categoryCreateDTO){
        Category category = new Category();
        category.setCategoryName(categoryCreateDTO.getCategoryName());

        boolean created = categoryService.create(category);
        if(!created){
            return new ResponseEntity<>("Category name exists", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Create Success", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CategoryDTO categoryDTO){
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setCategoryName(categoryDTO.getCategoryName());

        boolean updated = categoryService.update(category);
        if (!updated){
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Update Success", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        boolean deleted = categoryService.delete(id);
        if(!deleted){
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }
}
