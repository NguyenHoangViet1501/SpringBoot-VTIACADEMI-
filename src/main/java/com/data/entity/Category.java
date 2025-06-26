package com.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "category_name", nullable = false, unique = true, length = 100)
    String categoryName;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    List<Course> courses;
}
