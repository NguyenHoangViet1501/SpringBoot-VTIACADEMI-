package com.data.service;

import com.data.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAll();
    List<Course> getByCategoryId(int categoryId);
    Course getById(int id);
    boolean create(Course course);
    boolean update(Course course);
    boolean delete(int id);
    List<Course> search(String keyword);
    boolean ExistCourseName(String courseName);
}
