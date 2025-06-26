package com.data.service;

import com.data.dto.CertificateCreateDTO;
import com.data.entity.Certificate;

import java.util.List;

public interface CertificateService {

    List<Certificate> getAll();

    List<Certificate> getByCourseId(int courseId);

    Certificate createCertificate(int courseId, CertificateCreateDTO dto); //tao dua vao ket qua hoc
}
