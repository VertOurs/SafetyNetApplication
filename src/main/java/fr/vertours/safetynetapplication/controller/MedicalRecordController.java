package fr.vertours.safetynetapplication.controller;

import fr.vertours.safetynetapplication.dto.MedicalRecordDTO;
import fr.vertours.safetynetapplication.model.MedicalRecord;
import fr.vertours.safetynetapplication.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/medicalRecord")
public class MedicalRecordController {


    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }


    @GetMapping
    public List<MedicalRecordDTO> getListOfMedicalRecord() {
        List <MedicalRecord> medicalRecordList = medicalRecordService.getAllMedicalRecord();
        List <MedicalRecordDTO> medicalRecordDTOList = medicalRecordList.stream().map(MedicalRecordDTO::fromMedicalRecord).collect(Collectors.toList());
        return medicalRecordDTOList;
    }

    @GetMapping(path = "{lastName}/{firstName}")
    public MedicalRecordDTO getOneMedicalRecord(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
        MedicalRecord medicalRecord = medicalRecordService.getOneMedicalRecordByLastAndFirstName(lastName, firstName);
        return MedicalRecordDTO.fromMedicalRecord(medicalRecord);
    }
    @PostMapping
    public void registerNewMedicalPerson(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        medicalRecordService.save(medicalRecordDTO);
    }
    //ne fonctionne pas
    @PutMapping (path = "{lastName}/{firstName}")
    public void updateMedicalRecord(@PathVariable("lastName") String lastName,
                                    @PathVariable("firstName") String firstName,
                                    @RequestBody MedicalRecordDTO medicalRecordDTO) {
        System.out.println(medicalRecordDTO);
        medicalRecordService.updateMedicalRecord(lastName, firstName, medicalRecordDTO);

    }
    // Id ne doit pas etre null
    @DeleteMapping(path = "{lastName}/{firstName}")
    public void deleteOneMedicalRecord(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
        medicalRecordService.deleteOneMedicalRecord(firstName, lastName);
    }



}
