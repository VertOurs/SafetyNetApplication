package fr.vertours.safetynetapplication.service;

import fr.vertours.safetynetapplication.dto.MedicalRecordDTO;
import fr.vertours.safetynetapplication.model.Allergy;
import fr.vertours.safetynetapplication.model.MedicalRecord;
import fr.vertours.safetynetapplication.model.Medication;
import fr.vertours.safetynetapplication.model.Person;
import fr.vertours.safetynetapplication.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private AllergyService allergyService;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecord save(MedicalRecordDTO medicalRecord) {
        Person person = personService.find(medicalRecord.getFirstName(), medicalRecord.getLastName());
        LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DATE_TIME_FORMATTER);
        Set<String> medicationSet = medicalRecord.getMedications();
        Set<String> allergySet = medicalRecord.getAllergies();
        Set<Medication> setObjectMedication = makeMedication(medicationSet);
        Set<Allergy> setObjectAllergy = makeAllergy(allergySet);

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setPerson(person);
        medicalRecord1.setBirthDate(birthDate);
        medicalRecord1.setAllergies(setObjectAllergy);
        medicalRecord1.setMedications(setObjectMedication);

        return medicalRecordRepository.save(medicalRecord1);

    }

    private Set<Medication> makeMedication(Set<String> medicationName) {
        Set<Medication> setMedication = new HashSet<>();
        for(String s : medicationName) {
            Medication medication = medicationService.find(s);
            if(medication == null) {
                medication = medicationService.save(s);
            }
            setMedication.add(medication);
        }
        return setMedication;
    }

    private Set<Allergy> makeAllergy(Set<String> allergyName) {
        Set<Allergy> setAllergy = new HashSet<>();
        for(String s : allergyName){
            Allergy allergy = allergyService.find(s);
            if(allergy == null) {
                allergy = allergyService.save(s);
            }
            setAllergy.add(allergy);
        }
        return setAllergy;
    }
}
