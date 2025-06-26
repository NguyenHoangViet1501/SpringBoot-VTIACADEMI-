package com.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "type", nullable = false, length = 50)
    String type; // Associate, Professional, Master

    @Column(name = "course_id", nullable = false)
    int courseId;

    @Column(name = "student_id", nullable = false)
    int studentId;

    @Column(name = "final_score", nullable = false)
    double finalScore;
}
