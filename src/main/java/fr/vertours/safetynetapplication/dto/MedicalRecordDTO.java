package fr.vertours.safetynetapplication.dto;

import fr.vertours.safetynetapplication.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MedicalRecordDTO {

    private String firstName;
    private String lastName;
    private String birthdate;
    private Set<String> medications = new HashSet<>();
    private Set<String> allergies = new HashSet<>();

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    public String toString() {
        return "MedicalRecordDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + getBirthdate() + '\'' +
                ", medications='" + medications + '\'' +
                ", allergies='" + allergies + '\'' +
                '}';
    }
    public MedicalRecord createMedicalRecord() {
        Address address = new Address("Address non renseigné");
        Person person = new Person(getFirstName(),getLastName(),address, "city non renseigné", "zip non renseigné", "Phone non renseigné", "mail non renseigné");
        LocalDate birthDate = LocalDate.parse(getBirthdate());
        Set<Medication> MedicationSert = null;
        for(String s : getMedications()) {
            Medication medication = new Medication(s);
            MedicationSert.add(medication);
        }
        Set<Allergy> allergySet = null;
        for(String s : getAllergies()) {
            Allergy allergy = new Allergy(s);
            allergySet.add(allergy);
        }
        MedicalRecord medicalRecord = new MedicalRecord(person, birthDate, MedicationSert, allergySet);
        return medicalRecord;
    }
    public static MedicalRecordDTO fromMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        Set<String> allergySet = medicalRecord.getAllergies().stream().map(Allergy::getAllergy).collect(Collectors.toSet());
        medicalRecordDTO.setAllergies(allergySet);
        Set<String> medicationSet = medicalRecord.getMedications().stream().map(Medication::getMedication).collect(Collectors.toSet());
        medicalRecordDTO.setMedications(medicationSet);
        medicalRecordDTO.setBirthdate(medicalRecord.getBirthDate().format(DATE_TIME_FORMATTER));
        medicalRecordDTO.setFirstName(medicalRecord.getPerson().getFirstName());
        medicalRecordDTO.setLastName(medicalRecord.getPerson().getLastName());
        return medicalRecordDTO;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Set<String> getMedications() {
        return medications;
    }

    public void setMedications(Set<String> medications) {
        this.medications = medications;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<String> allergies) {
        this.allergies = allergies;
    }
}