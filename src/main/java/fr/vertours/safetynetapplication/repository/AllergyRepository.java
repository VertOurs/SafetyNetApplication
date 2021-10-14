package fr.vertours.safetynetapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<Person, Long> {
    
}
