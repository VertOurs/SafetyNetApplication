package fr.vertours.safetynetapplication.repository;


import fr.vertours.safetynetapplication.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {



    
}
