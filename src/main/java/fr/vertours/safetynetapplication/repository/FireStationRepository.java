package fr.vertours.safetynetapplication.repository;

import fr.vertours.safetynetapplication.model.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireStationRepository extends JpaRepository<FireStation, Long> {

}
