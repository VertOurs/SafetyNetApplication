package fr.vertours.safetynetapplication.dto;

import java.util.HashSet;
import java.util.Set;

public class MedicalRecordDTO {

    private String firstName;
    private String lastName;
    private String birthDate;
    private Set<String> medications = new HashSet<>();
    private Set<String> allergies = new HashSet<>();


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

    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    @Override
    public String toString() {
        return "MedicalRecordDTO{"
                + "firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", birthDate='" + birthDate + '\''
                + ", medications='" + medications + '\''
                + ", allergies='" + allergies + '\''
                + '}';
    }
}
