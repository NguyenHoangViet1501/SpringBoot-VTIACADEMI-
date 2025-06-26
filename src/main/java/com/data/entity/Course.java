package com.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "course_name", nullable = false, length = 100)
    String courseName;

    @Column(name = "hours", nullable = false)
    int hours;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
