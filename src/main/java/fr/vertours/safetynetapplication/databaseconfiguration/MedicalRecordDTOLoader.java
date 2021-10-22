package fr.vertours.safetynetapplication.databaseconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import fr.vertours.safetynetapplication.dto.MedicalRecordDTO;
import fr.vertours.safetynetapplication.model.MedicalRecord;
import fr.vertours.safetynetapplication.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;



public class MedicalRecordDTOLoader {

    @Autowired
    MedicalRecordService medicalRecordService;

    public void saveMedicalRecordInDB (Map<String, Object> map, ObjectMapper objectMapper) {
        List<Object> listOfMedicalRecordDTO = (List<Object>) map.get("medicalrecords");
        for(Object medicalRecord : listOfMedicalRecordDTO) {
            MedicalRecordDTO medicalRecordDTO = objectMapper.convertValue(medicalRecord, MedicalRecordDTO.class);
            medicalRecordService.save(medicalRecordDTO);
        }
    }

}
