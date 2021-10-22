package fr.vertours.safetynetapplication.repository;

import fr.vertours.safetynetapplication.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    MedicalRecord findOneByPerson_FirstNameAndPerson_LastName(String firstName, String lastName);
    Long findByPerson_FirstNameAndPerson_LastName(String firstName, String lastName);
}
