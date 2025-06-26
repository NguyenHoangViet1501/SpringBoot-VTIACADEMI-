package com.data.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateDTO {
    int id;

    String type;

    int courseId;

    int studentId;

    double finalScore;
}
