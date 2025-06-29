package com.data.controller;

import com.data.dto.CourseCrerateDTO;
import com.data.dto.CourseDTO;
import com.data.entity.Category;
import com.data.entity.Course;
import com.data.exception.AppException;
import com.data.exception.ErrorCode;
import com.data.repository.CourseRepository;
import com.data.service.CategoryService;
import com.data.service.CertificateService;
import com.data.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseController {

    private CourseService courseService;
    private CategoryService categoryService;

    public CourseController(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Course> courses = courseService.getAll();

        List<CourseDTO> courseDTOS = new ArrayList<>();

        courses.forEach(course -> {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setSessions(course.getSessions());
            courseDTO.setHours(course.getHours());
            if(course.getCategory() != null){
                courseDTO.setCategoryName(course.getCategory().getCategoryName());
            }
            courseDTOS.add(courseDTO);
        });
        return new ResponseEntity<>(courseDTOS,HttpStatus.OK);
    }


    @GetMapping("category/{id}")
    public ResponseEntity<?> getByCategoryId(@PathVariable("id") int categoryId){
        List<Course> courses = courseService.getByCategoryId(categoryId);

        List<CourseDTO> courseDTOS = new ArrayList<>();

        courses.forEach(course -> {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setSessions(course.getSessions());
            courseDTO.setHours(course.getHours());
            if(course.getCategory() != null){
                courseDTO.setCategoryName(course.getCategory().getCategoryName());
            }
            courseDTOS.add(courseDTO);
        });

        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable("id") int id){
        Course course = courseService.getById(id);
        if (course == null){
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setSessions(course.getSessions());
        courseDTO.setHours(course.getHours());
        if(course.getCategory() != null){
            courseDTO.setCategoryName(course.getCategory().getCategoryName());
        }
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CourseCrerateDTO courseCrerateDTO){
        if(courseService.ExistCourseName(courseCrerateDTO.getCourseName())){
            throw new AppException(ErrorCode.COURSE_EXIST);
        }
        if(!categoryService.existCategory(courseCrerateDTO.getCategoryId())){
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        Category category = categoryService.getById(courseCrerateDTO.getCategoryId());
        Course course = new Course();
        course.setCourseName(courseCrerateDTO.getCourseName());
        course.setHours(courseCrerateDTO.getHours());
        course.setSessions(courseCrerateDTO.getSessions());
        course.setCategory(category);

        courseService.create(course);

        return new ResponseEntity<>("Create success", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody CourseCrerateDTO courseCrerateDTO){
        if (courseService.getById(id) == null) {
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }
        if (!categoryService.existCategory(courseCrerateDTO.getCategoryId())) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        Course course = new Course();
        Category category = categoryService.getById(courseCrerateDTO.getCategoryId());
        course.setId(id);
        course.setCourseName(courseCrerateDTO.getCourseName());
        course.setHours(courseCrerateDTO.getHours());
        course.setSessions(courseCrerateDTO.getSessions());
        course.setCategory(category);

        courseService.update(course);
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        if (courseService.getById(id) == null) {
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }
        courseService.delete(id);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("q") String keyword){
        List<Course> courses = courseService.search(keyword);
        if (courses.isEmpty()){
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }
        List<CourseDTO> courseDTOS = new ArrayList<>();

        courses.forEach(course -> {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setSessions(course.getSessions());
            courseDTO.setHours(course.getHours());
            if(course.getCategory() != null){
                courseDTO.setCategoryName(course.getCategory().getCategoryName());
            }
            courseDTOS.add(courseDTO);
        });

        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
    }
}
