package com.data.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseCreateDTO {

    @NotBlank(message = "COURSE_NAME_REQUIRED")
    @Size(min = 2, max = 100, message = "INVALID_COURSE_NAME_LENGTH")
    String courseName;

    @Min(value = 0, message = "INVALID_HOURS")
    Integer hours;

    @Min(value = 0, message = "INVALID_SESSION_COUNT")
    Integer sessions;

    @NotNull(message = "CATEGORY_NOT_FOUND")
    Integer categoryId;
}
