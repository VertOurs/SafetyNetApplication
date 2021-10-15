package fr.vertours.safetynetapplication.repository;

import fr.vertours.safetynetapplication.model.MedicalRecord;
import fr.vertours.safetynetapplication.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Medication findOneByMedication(String medication);
}
