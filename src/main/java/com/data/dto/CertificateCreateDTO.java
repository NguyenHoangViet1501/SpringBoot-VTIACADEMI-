package com.data.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateCreateDTO {
    String type;

    int studentId;

    double finalScore;

}
