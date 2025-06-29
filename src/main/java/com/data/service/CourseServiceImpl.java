package com.data.service;

import com.data.entity.Course;
import com.data.repository.CategoryRepository;
import com.data.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;
    private CategoryRepository categoryRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getByCategoryId(int categoryId) {
        return courseRepository.findByCategoryId(categoryId);
    }

    @Override
    public Course getById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public boolean create(Course course) {
        courseRepository.save(course);
        return true;
    }

    @Override
    public boolean update(Course course) {
        courseRepository.save(course);
        return true;
    }

    @Override
    public boolean delete(int id) {
        courseRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Course> search(String keyword) {
        return courseRepository.findByCourseNameContainingIgnoreCase(keyword);
    }

    @Override
    public boolean ExistCourseName(String courseName) {
        return courseRepository.existsByCourseName(courseName);
    }


}
