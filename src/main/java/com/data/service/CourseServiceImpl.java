package com.data.service;

import com.data.entity.Course;
import com.data.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getByCategoryId(int categoryId) {
        return courseRepository.findByCategoryId(categoryId);
    }
}
