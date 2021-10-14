package fr.vertours.safetynetapplication.databaseconfiguration;

import fr.vertours.safetynetapplication.model.MedicalRecord;
import fr.vertours.safetynetapplication.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
public class MedicalRecordLoader {

    @Value("classpath:Json/data.json")
    Resource resource;

    @Autowired
    MedicalRecordService medicalRecordService;
}
