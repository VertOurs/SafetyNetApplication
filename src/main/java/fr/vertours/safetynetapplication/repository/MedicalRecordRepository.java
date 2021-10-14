package fr.vertours.safetynetapplication.repository;

import fr.vertours.safetynetapplication.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
