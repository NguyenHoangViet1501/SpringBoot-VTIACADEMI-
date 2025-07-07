package com.data.exception;

import com.data.dto.AuthCreateDTO;
import com.data.dto.CourseCreateDTO;
import com.data.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalWebExceptionHandler {

    private final CategoryService categoryService; // Inject để dùng cho course form

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException ex, Model model, HttpServletRequest request) {
        String key = ex.getBindingResult().getFieldError().getDefaultMessage();
        String errorMsg;
        try {
            errorMsg = ErrorCode.valueOf(key).getMesagge();
        } catch (Exception e) {
            errorMsg = key;
        }

        model.addAttribute("error", errorMsg);

        // Lấy uri để xác định loại form đang xử lý
        String uri = request.getRequestURI();

        if (uri.contains("/register")) {
            model.addAttribute("authCreateDTO", ex.getBindingResult().getTarget());
            return "AuthRegister";
        }

        if (uri.contains("/create")) {
            model.addAttribute("courseCreateDTO", ex.getBindingResult().getTarget());
            model.addAttribute("categories", categoryService.getAll());
            return "CourseCreate";
        }

        if (uri.contains("/edit")) {
            model.addAttribute("courseCreateDTO", ex.getBindingResult().getTarget());
            model.addAttribute("categories", categoryService.getAll());
            return "CourseEdit";
        }

        return "error";
    }

    @ExceptionHandler(AppException.class)
    public String handleAppException(AppException ex, Model model, HttpServletRequest request) {
        model.addAttribute("error", ex.getErrorCode().getMesagge());

        String uri = request.getRequestURI();

        if (uri.contains("/register")) {
            model.addAttribute("authCreateDTO", new AuthCreateDTO());
            return "AuthRegister";
        }

        if (uri.contains("/create")) {
            model.addAttribute("courseCreateDTO", new CourseCreateDTO());
            model.addAttribute("categories", categoryService.getAll());
            return "CourseCreate";
        }

        if (uri.contains("/edit")) {
            model.addAttribute("courseCreateDTO", new CourseCreateDTO());
            model.addAttribute("categories", categoryService.getAll());
            return "CourseEdit";
        }

        return "error";
    }
}
