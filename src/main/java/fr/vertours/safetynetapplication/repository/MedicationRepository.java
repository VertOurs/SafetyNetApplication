package fr.vertours.safetynetapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<MedicalRecord, Long> {

}
