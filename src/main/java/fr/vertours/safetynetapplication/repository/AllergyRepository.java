package fr.vertours.safetynetapplication.repository;

import fr.vertours.safetynetapplication.model.Allergy;
import fr.vertours.safetynetapplication.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    Allergy findOneByAllergy(String allergyName);
}
