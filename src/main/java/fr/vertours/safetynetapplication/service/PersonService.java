package fr.vertours.safetynetapplication.service;

import fr.vertours.safetynetapplication.dto.PersonDTO;
import fr.vertours.safetynetapplication.model.Person;
import fr.vertours.safetynetapplication.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    PersonDTO personDTO;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public void savePerson(){
        personRepository.save(personDTO.createPerson());
    }

    public void saveAll(Collection<Person> collection) {
        personRepository.saveAll(collection);
    }

    public Person find(String firstName, String lastName) {
        return personRepository.findOneByFirstNameAndLastName(firstName,lastName);
    }



    //********************************************
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }
    
    public void deletePerson(Long personId) {
        boolean exists =  personRepository.existsById(personId);
        if (!exists) {
            throw new IllegalStateException("Person with this id : "+personId+", does not exists");
        }
        personRepository.deleteById(personId);
    }
}
