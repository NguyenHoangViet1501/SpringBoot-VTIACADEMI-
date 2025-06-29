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

    @Column(name = "course_name",unique = true ,nullable = false, length = 100)
    String courseName;

    @Column(name = "sessions",nullable = false)
    int sessions = 0;

    @Column(name = "hours", nullable = false)
    int hours = 0;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
