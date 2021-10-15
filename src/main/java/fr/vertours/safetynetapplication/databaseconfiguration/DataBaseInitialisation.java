package fr.vertours.safetynetapplication.databaseconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import fr.vertours.safetynetapplication.dto.MedicalRecordDTO;
import fr.vertours.safetynetapplication.dto.PersonDTO;
import fr.vertours.safetynetapplication.model.Address;
import fr.vertours.safetynetapplication.model.FireStation;
import fr.vertours.safetynetapplication.model.Person;
import fr.vertours.safetynetapplication.service.AddressService;
import fr.vertours.safetynetapplication.service.FireStationService;
import fr.vertours.safetynetapplication.service.MedicalRecordService;
import fr.vertours.safetynetapplication.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Configuration
public class DataBaseInitialisation {
    @Value("classpath:Json/data.json")
    Resource resource;

    @Autowired
    AddressService addressService;

    @Autowired
    PersonService personService;

    @Autowired
    FireStationService fireStationService;

    @Autowired
    MedicalRecordService medicalRecordService;

    @Bean
    CommandLineRunner commandLineRunner() {

        return args -> {

//*********************** Mise en place du JSON
            Map<String, Object> map = deserializeJson();

            ObjectMapper objectMapper = new ObjectMapper();
            Set<Address> addressSet = new HashSet();
            Set<Person> personSet = new LinkedHashSet();

//********************** constitution de la set d'adresse et la set de person des personnes (map, objectmapper, addressSet, PersonnSet)-> retour Set d'adresse.
            List<Object> listOfPersonDTO = (List<Object>) map.get("persons");       //********************          ????   Person


            for(Object o : listOfPersonDTO) {                                           // j'itere sur une liste d'objet ayant que des attributs string
                PersonDTO personDTO = objectMapper.convertValue(o, PersonDTO.class);    //je crée une personnDTO ayant les caracteristique du JSON

                Address address = new Address();                                        // je crée un objet adresse vide
                address.setAddressName(personDTO.getAddress());                         //je set l'attribut AddressName(String) par l'attribut adresse de mon personDTO
                if (!addressSet.contains(address)) {                                    //Si mon objet adress n'est pas déja dans ma set d'adresse...
                    addressSet.add(address);                                            //j'ajoute mon objet adresse
                }
                Person person = personDTO.createPerson();                               // Je crée un objet person qui coresspond a personnDTO avec des champs STring sauf un champ objet adresse

                personSet.add(person);                                                  // je rajoute mes personnes dans ma set.
                                                                                    //*********************            ?????  Person
            }
            List<Address> addressList = addressService.saveAll(addressSet);             // je crée une liste d'adresse avec l'adresseSet deja rempli d'adresse
            for(Person person : personSet){                                             //jitere sur chaque personne de ma set de personne
                Address personAddress = person.getAddress();                            // a chaque fois je crée un objet adresse qui corespond a l'adress dema personn

                Address address = addressList.stream().filter((addressa)->              // je sais pas ????
                        addressa.getAddressName().equals(personAddress
                                .getAddressName())).findFirst().get();

                person.setAddress(address);                                             //je setl'adresse de ma personne par l'objet adresse
            }
            personService.saveAll(personSet);                                           // je save ma set de personne qui possedent tous les objet adresse qui vont bien.




//*****************************  SAVE des FireStation(map, objectmapper)-> retour void
            saveFireStationInDB(map, objectMapper, addressList);

//*********************** SAVE MedicalRecord
            saveMedicalRecordInDB(map, objectMapper);
        };
    }

    public Map<String, Object> deserializeJson() throws IOException {
        InputStream input = resource.getInputStream();
        String data = Files.readString(Paths.get(resource.getURI()));
        Map<String, Object> map = JsonIterator.deserialize(data, Map.class);

        return map;
    }

    public void saveMedicalRecordInDB (Map<String, Object> map, ObjectMapper objectMapper) {
        List<Object> listOfMedicalRecordDTO = (List<Object>) map.get("medicalrecords");
        for(Object medicalRecord : listOfMedicalRecordDTO) {
            MedicalRecordDTO medicalRecordDTO = objectMapper.convertValue(medicalRecord, MedicalRecordDTO.class);
            medicalRecordService.save(medicalRecordDTO);
        }
    }
    public void saveFireStationInDB (Map<String, Object> map, ObjectMapper objectMapper, List<Address> addressList) {
        List<Object> listOfFireStationDTO = (List<Object>) map.get("firestations");
        List<FireStation> fireStationsList = new ArrayList<>();
        for(Object fireStation : listOfFireStationDTO) {
            Map<String, String> fireStationDTO = objectMapper.convertValue(fireStation, Map.class);
            FireStation fireStation1 = new FireStation();
            fireStation1.setStation((Integer.valueOf(fireStationDTO.get("station"))));
            Address address = addressList.stream().filter((addressa)->addressa.getAddressName().equals(fireStationDTO.get("address"))).findFirst().get();
            fireStation1.addAdress(address);
            int index = fireStationsList.indexOf(fireStation1);
            if(index == -1){
                fireStationsList.add(fireStation1);
            } else {
                FireStation fireStation2 = fireStationsList.get(index);
                fireStation2.addAdress(address);
            }
        }

        fireStationService.saveAll(fireStationsList);
    }

}
