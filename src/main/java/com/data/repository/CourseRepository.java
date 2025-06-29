package com.data.repository;

import com.data.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByCategoryId(int categoryId);

    List<Course> findByCourseNameContainingIgnoreCase(String keyword);

    boolean existsByCourseName(String courseName);

}
