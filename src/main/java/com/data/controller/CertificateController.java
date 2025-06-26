package com.data.controller;

import com.data.dto.CertificateCreateDTO;
import com.data.dto.CertificateDTO;
import com.data.entity.Certificate;
import com.data.service.CertificateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("certificates")
public class CertificateController {
    private CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Certificate> certificates = certificateService.getAll();

        List<CertificateDTO> certificateDTOS = new ArrayList<>();

        certificates.forEach( certificate -> {
            CertificateDTO certificateDTO = new CertificateDTO();
            certificateDTO.setId(certificate.getId());
            certificateDTO.setType(certificate.getType());
            certificateDTO.setCourseId(certificate.getCourseId());
            certificateDTO.setStudentId(certificate.getStudentId());
            certificateDTO.setFinalScore(certificate.getFinalScore());
            certificateDTOS.add(certificateDTO);
        });

        return new ResponseEntity<>(certificateDTOS, HttpStatus.OK);
    }

    @GetMapping("{courseId}")
    public ResponseEntity<?> getByCourseId(@PathVariable int courseId) {
        List<Certificate> certificates = certificateService.getByCourseId(courseId);
        List<CertificateDTO> certificateDTOS = new ArrayList<>();

        certificates.forEach( certificate -> {
            CertificateDTO certificateDTO = new CertificateDTO();
            certificateDTO.setId(certificate.getId());
            certificateDTO.setType(certificate.getType());
            certificateDTO.setCourseId(certificate.getCourseId());
            certificateDTO.setStudentId(certificate.getStudentId());
            certificateDTO.setFinalScore(certificate.getFinalScore());
            certificateDTOS.add(certificateDTO);
        });

        return new ResponseEntity<>(certificateDTOS, HttpStatus.OK);
    }

    @PostMapping("{courseId}")
    public ResponseEntity<?> create(@PathVariable int courseId, @RequestBody CertificateCreateDTO certificateCreateDTO){
        Certificate certificate = certificateService.createCertificate(courseId, certificateCreateDTO);

        CertificateDTO certificateDTO = new CertificateDTO();

        certificateDTO.setType(certificate.getType());
        certificateDTO.setCourseId(certificate.getCourseId());
        certificateDTO.setStudentId(certificate.getStudentId());
        certificateDTO.setFinalScore(certificate.getFinalScore());

        return new ResponseEntity<>(certificateDTO,HttpStatus.CREATED);
    }
}
