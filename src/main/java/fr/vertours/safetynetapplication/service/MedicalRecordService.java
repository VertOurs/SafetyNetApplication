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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


    public List<MedicalRecord> getAllMedicalRecord() {
        return medicalRecordRepository.findAll();
    }
    public MedicalRecord getOneMedicalRecordByLastAndFirstName(String lastName, String firstName) {
        return medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(firstName, lastName);
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



    public void updateMedicalRecord(String lastName, String firstName, MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(firstName, lastName);

        if(medicalRecordDTO.getBirthdate() != null ) {
            LocalDate localBirthDate = LocalDate.parse(medicalRecordDTO.getBirthdate(), DATE_TIME_FORMATTER);
            System.out.println(medicalRecordDTO.getBirthdate());
            System.out.println(localBirthDate);
            medicalRecord.setBirthDate(localBirthDate);
        }
        if(medicalRecordDTO.getMedications() != null ) {
            if (medicalRecordDTO.getMedications().size() > 0) {
                Set<Medication> medicationSet = medicalRecordDTO.getMedications().stream().map(medication -> medicationService.findOrCreate(medication)).collect(Collectors.toSet());
                medicalRecord.setMedications(medicationSet);
            } else {
                medicalRecord.removeAllMedications();
            }
        }
        if(medicalRecordDTO.getAllergies() != null ) {
            if (medicalRecordDTO.getAllergies().size() > 0 ){
                Set<Allergy> allergySet = medicalRecordDTO.getAllergies().stream().map(allergy -> allergyService.findOrCreate(allergy) ).collect(Collectors.toSet());
                medicalRecord.setAllergies(allergySet);
            } else {
                medicalRecord.removeAllAllergies();
            }
        }
        medicalRecordRepository.save(medicalRecord);
    }

    public void deleteOneMedicalRecord(String firstName, String lastName) {
        Long id = medicalRecordRepository.findByPerson_FirstNameAndPerson_LastName(firstName, lastName);
        medicalRecordRepository.deleteById(id);
    }
}
