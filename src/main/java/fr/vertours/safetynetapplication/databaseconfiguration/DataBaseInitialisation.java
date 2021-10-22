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

    PersonDTOLoader personLoader;
    FireStationDTOLoader fireStationLoader;
    MedicalRecordDTOLoader medicalRecordLoader;



    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            Map<String, Object> map = deserializeJson();

            ObjectMapper objectMapper = new ObjectMapper();
            Set<Address> addressSet = personLoader.returnSetOfAddressInPersonDTO(map, objectMapper);
            Set<Person> personSet = personLoader.returnSetOfPersonInPersonDTO(map, objectMapper);
            List<Address> addressList = addressService.saveAll(addressSet);

            personLoader.savePersonAndAddressInDB(map, objectMapper, addressList,personSet);
            fireStationLoader.saveFireStationInDB(map, objectMapper, addressList);
            medicalRecordLoader.saveMedicalRecordInDB(map, objectMapper);
        };
    }


    public Map<String, Object> deserializeJson() throws IOException {
        InputStream input = resource.getInputStream();
        String data = Files.readString(Paths.get(resource.getURI()));
        Map<String, Object> map = JsonIterator.deserialize(data, Map.class);
        return map;
    }




}
