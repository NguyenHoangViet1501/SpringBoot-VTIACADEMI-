package com.data.service;

import com.data.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getByCategoryId(int categoryId);
}
