package fr.vertours.safetynetapplication.controller;

import fr.vertours.safetynetapplication.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }
}