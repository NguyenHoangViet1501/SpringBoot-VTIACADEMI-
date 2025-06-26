package com.data.service;

import com.data.dto.CertificateCreateDTO;
import com.data.entity.Certificate;
import com.data.repository.CertificateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService{

    private CertificateRepository certificateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository){
        this.certificateRepository = certificateRepository;
    }

    @Override
    public List<Certificate> getAll() {
        return certificateRepository.findAll();
    }

    @Override
    public List<Certificate> getByCourseId(int courseId) {
        return certificateRepository.findByCourseId(courseId);
    }

    @Override
    public Certificate createCertificate(int courseId, CertificateCreateDTO dto) {
        Certificate c = new Certificate();

        c.setCourseId(courseId);
        c.setType(dto.getType());
        c.setStudentId(dto.getStudentId());
        c.setFinalScore(dto.getFinalScore());


        return certificateRepository.save(c);
    }
}
