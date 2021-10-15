package fr.vertours.safetynetapplication.repository;

import fr.vertours.safetynetapplication.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstName(String name);

    Person findOneByFirstNameAndLastName(String firstName, String lastName);
}
