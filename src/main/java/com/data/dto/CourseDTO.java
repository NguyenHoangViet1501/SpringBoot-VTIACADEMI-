package com.data.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDTO {

    int id;

    String courseName;

    int hours;

    int sessions;

    String categoryName;
}
