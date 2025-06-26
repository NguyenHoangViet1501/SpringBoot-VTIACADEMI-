package com.data.controller;

import com.data.dto.CourseDTO;
import com.data.entity.Course;
import com.data.repository.CourseRepository;
import com.data.service.CategoryService;
import com.data.service.CertificateService;
import com.data.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("category/{id}")
    public ResponseEntity<?> getByCategoryId(@PathVariable("id") int categoryId){
        List<Course> courses = courseService.getByCategoryId(categoryId);

        List<CourseDTO> courseDTOS = new ArrayList<>();

        courses.forEach(course -> {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setHours(course.getHours());
            if(course.getCategory() != null){
                courseDTO.setCategoryName(course.getCategory().getCategoryName());
            }
            courseDTOS.add(courseDTO);
        });

        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
    }
}
