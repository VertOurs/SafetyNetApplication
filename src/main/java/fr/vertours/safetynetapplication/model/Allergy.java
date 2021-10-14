package fr.vertours.safetynetapplication.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true, nullable = false)
    private String allergy;

    @ManyToMany (mappedBy = "allergies")
    private Set<MedicalRecord> medicalRecord;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAllergy() {
        return allergy;
    }
    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public Set<MedicalRecord> getMedicalRecord() {
        return medicalRecord;
    }
    public void setMedicalRecord(Set<MedicalRecord> medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
