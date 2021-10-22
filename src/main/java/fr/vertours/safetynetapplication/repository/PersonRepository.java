package fr.vertours.safetynetapplication.repository;

import fr.vertours.safetynetapplication.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByFirstName(String firstName);
    Person findOneByFirstNameAndLastName(String firstName, String lastName);
    Person findOneById(Long ID);
    Person deleteByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findByAddressIn();


}
