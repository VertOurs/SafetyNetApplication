package fr.vertours.safetynetapplication.dto;

public class MedicalRecordDTO {

    private String firstName;
    private String lastName;
    private String birthDate;
    private String medications;
    private String allergies;


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

    public String getMedications() {
        return medications;
    }
    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getAllergies() {
        return allergies;
    }
    public void setAllergies(String allergies) {
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
