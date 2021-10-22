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

//*********************** Variable qui vont bien.
            ObjectMapper objectMapper = new ObjectMapper();
            Set<Address> addressSet = returnSetOfAddressInPersonDTO(map, objectMapper);
            Set<Person> personSet = returnSetOfPersonInPersonDTO(map, objectMapper);
            List<Address> addressList = addressService.saveAll(addressSet);

//********************** Save Person et Address
            savePersonAndAddressInDB(map, objectMapper, addressList,personSet);

//*****************************  SAVE des FireStation(map, objectmapper)-> retour void
            saveFireStationInDB(map, objectMapper, addressList);

//*********************** SAVE MedicalRecord
            saveMedicalRecordInDB(map, objectMapper);
        };
    }


    public Set<Person> returnSetOfPersonInPersonDTO(Map<String, Object> map, ObjectMapper objectMapper){
        Set<Person> personSet = new LinkedHashSet();
        List<Object> listOfPersonDTO = (List<Object>) map.get("persons");
        for(Object o : listOfPersonDTO) {
            PersonDTO personDTO = objectMapper.convertValue(o, PersonDTO.class);
            Person person = personDTO.createPerson();
            personSet.add(person);
        }
        return personSet;
    }

    public Set<Address> returnSetOfAddressInPersonDTO(Map<String, Object> map, ObjectMapper objectMapper){
        Set<Address> addressSet = new HashSet();
        List<Object> listOfPersonDTO = (List<Object>) map.get("persons");
        for(Object o : listOfPersonDTO) {
            PersonDTO personDTO = objectMapper.convertValue(o, PersonDTO.class);
            Address address = new Address();
            address.setAddressName(personDTO.getAddress());
            if (!addressSet.contains(address)) {
                addressSet.add(address);
            }
        }

        return addressSet;
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
        fireStationService.saveAllStations(fireStationsList);
    }
    public void savePersonAndAddressInDB (Map<String, Object> map, ObjectMapper objectMapper, List<Address> addressList, Set<Person> personSet) {
        for(Person person : personSet){
            Address personAddress = person.getAddress();

            Address address = addressList.stream().filter((addressa)->
                    addressa.getAddressName().equals(personAddress
                            .getAddressName())).findFirst().get();

            person.setAddress(address);
        }
        personService.saveAll(personSet);

    }
}
