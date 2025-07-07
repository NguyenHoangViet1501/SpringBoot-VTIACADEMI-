package com.data.controller;

import com.data.dto.CourseCreateDTO;
import com.data.entity.Category;
import com.data.entity.Course;
import com.data.exception.AppException;
import com.data.exception.ErrorCode;
import com.data.service.CategoryService;
import com.data.service.CourseService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("courses")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {

    CourseService courseService;
    CategoryService categoryService;


//    @GetMapping
//    public ResponseEntity<?> getAll(){
//        List<Course> courses = courseService.getAll();
//
//        List<CourseDTO> courseDTOS = new ArrayList<>();
//
//        courses.forEach(course -> {
//            CourseDTO courseDTO = new CourseDTO();
//            courseDTO.setId(course.getId());
//            courseDTO.setCourseName(course.getCourseName());
//            courseDTO.setSessions(course.getSessions());
//            courseDTO.setHours(course.getHours());
//            if(course.getCategory() != null){
//                courseDTO.setCategoryName(course.getCategory().getCategoryName());
//            }
//            courseDTOS.add(courseDTO);
//        });
//        return new ResponseEntity<>(courseDTOS,HttpStatus.OK);
//    }
//
//
//    @GetMapping("category/{id}")
//    public ResponseEntity<?> getByCategoryId(@PathVariable("id") int categoryId){
//        List<Course> courses = courseService.getByCategoryId(categoryId);
//
//        List<CourseDTO> courseDTOS = new ArrayList<>();
//
//        courses.forEach(course -> {
//            CourseDTO courseDTO = new CourseDTO();
//            courseDTO.setId(course.getId());
//            courseDTO.setCourseName(course.getCourseName());
//            courseDTO.setSessions(course.getSessions());
//            courseDTO.setHours(course.getHours());
//            if(course.getCategory() != null){
//                courseDTO.setCategoryName(course.getCategory().getCategoryName());
//            }
//            courseDTOS.add(courseDTO);
//        });
//
//        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getCourseById(@PathVariable("id") int id){
//        Course course = courseService.getById(id);
//        if (course == null){
//            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
//        }
//        CourseDTO courseDTO = new CourseDTO();
//        courseDTO.setId(course.getId());
//        courseDTO.setCourseName(course.getCourseName());
//        courseDTO.setSessions(course.getSessions());
//        courseDTO.setHours(course.getHours());
//        if(course.getCategory() != null){
//            courseDTO.setCategoryName(course.getCategory().getCategoryName());
//        }
//        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> create(@Valid @RequestBody CourseCrerateDTO courseCrerateDTO){
//        if(courseService.ExistCourseName(courseCrerateDTO.getCourseName())){
//            throw new AppException(ErrorCode.COURSE_EXIST);
//        }
//        if(!categoryService.existCategory(courseCrerateDTO.getCategoryId())){
//            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
//        }
//        Category category = categoryService.getById(courseCrerateDTO.getCategoryId());
//        Course course = new Course();
//        course.setCourseName(courseCrerateDTO.getCourseName());
//        course.setHours(courseCrerateDTO.getHours());
//        course.setSessions(courseCrerateDTO.getSessions());
//        course.setCategory(category);
//
//        courseService.create(course);
//
//        return new ResponseEntity<>("Create success", HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody CourseCrerateDTO courseCrerateDTO){
//        if (courseService.getById(id) == null) {
//            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
//        }
//        if (!categoryService.existCategory(courseCrerateDTO.getCategoryId())) {
//            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
//        }
//        Course course = new Course();
//        Category category = categoryService.getById(courseCrerateDTO.getCategoryId());
//        course.setId(id);
//        course.setCourseName(courseCrerateDTO.getCourseName());
//        course.setHours(courseCrerateDTO.getHours());
//        course.setSessions(courseCrerateDTO.getSessions());
//        course.setCategory(category);
//
//        courseService.update(course);
//        return new ResponseEntity<>("Update success", HttpStatus.OK);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<?> delete(@PathVariable int id){
//        if (courseService.getById(id) == null) {
//            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
//        }
//        courseService.delete(id);
//        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<?> search(@RequestParam("q") String keyword){
//        List<Course> courses = courseService.search(keyword);
//        if (courses.isEmpty()){
//            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
//        }
//        List<CourseDTO> courseDTOS = new ArrayList<>();
//
//        courses.forEach(course -> {
//            CourseDTO courseDTO = new CourseDTO();
//            courseDTO.setId(course.getId());
//            courseDTO.setCourseName(course.getCourseName());
//            courseDTO.setSessions(course.getSessions());
//            courseDTO.setHours(course.getHours());
//            if(course.getCategory() != null){
//                courseDTO.setCategoryName(course.getCategory().getCategoryName());
//            }
//            courseDTOS.add(courseDTO);
//        });
//
//        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
//    }

    @GetMapping("list")
    public String getAll(Model model){
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses", courses);
        model.addAttribute("categories", categoryService.getAll());
        return "CourseList";
    }

    @GetMapping("/detail/{id}")
    public String getCourseDetail(@PathVariable("id") int id, Model model) {
        Course course = courseService.getById(id);
        if (course == null) {
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }
        model.addAttribute("course", course);
        return "CourseDetail";
    }

    @GetMapping("/create")
    public String createCourseForm(Model model) {
        model.addAttribute("courseCreateDTO", new CourseCreateDTO());
        model.addAttribute("categories", categoryService.getAll());
        return "CourseCreate";
    }

    @PostMapping("/create")
    public String createCourse(@Valid @ModelAttribute CourseCreateDTO courseCreateDTO, Model model) {
        if (courseService.ExistCourseName(courseCreateDTO.getCourseName())) {
            throw new AppException(ErrorCode.COURSE_EXIST);
        }
        if (!categoryService.existCategory(courseCreateDTO.getCategoryId())) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        Category category = categoryService.getById(courseCreateDTO.getCategoryId());
        Course course = new Course();
        course.setCourseName(courseCreateDTO.getCourseName());
        course.setHours(courseCreateDTO.getHours());
        course.setSessions(courseCreateDTO.getSessions());
        course.setCategory(category);

        courseService.create(course);
        return "redirect:/courses/list";
    }

    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable("id") int id, Model model) {
        Course course = courseService.getById(id);
        CourseCreateDTO dto = new CourseCreateDTO();
        dto.setCourseName(course.getCourseName());
        dto.setHours(course.getHours());
        dto.setSessions(course.getSessions());
        dto.setCategoryId(course.getCategory() != null ? course.getCategory().getId() : null);

        model.addAttribute("courseCreateDTO", dto);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("courseId", id);
        return "CourseEdit";
    }

    @PostMapping("/edit/{id}")
    public String editCourse(@PathVariable("id") int id, @Valid @ModelAttribute CourseCreateDTO courseCreateDTO, Model model) {
        Course course = new Course();
        Category category = categoryService.getById(courseCreateDTO.getCategoryId());
        course.setId(id);
        course.setCourseName(courseCreateDTO.getCourseName());
        course.setHours(courseCreateDTO.getHours());
        course.setSessions(courseCreateDTO.getSessions());
        course.setCategory(category);

        courseService.update(course);
        return "redirect:/courses/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id) {
        courseService.delete(id);
        return "redirect:/courses/list";
    }

    @GetMapping("/search")
    public String searchCourses(@RequestParam("q") String keyword, Model model) {
        List<Course> courses = courseService.search(keyword);
        if (courses.isEmpty()){
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }
        model.addAttribute("courses", courses);
        model.addAttribute("categories", categoryService.getAll());
        return "CourseList";
    }

    @GetMapping("/search-course/{categoryId}")
    public String filterByCategory(@PathVariable("categoryId") int categoryId, Model model) {
        List<Course> courses = courseService.getByCategoryId(categoryId);
        model.addAttribute("courses", courses);
        model.addAttribute("categories", categoryService.getAll());
        return "CourseList";
    }
}
