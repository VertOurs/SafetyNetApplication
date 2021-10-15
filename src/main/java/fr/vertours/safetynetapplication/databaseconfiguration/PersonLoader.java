package fr.vertours.safetynetapplication.databaseconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import fr.vertours.safetynetapplication.dto.MedicalRecordDTO;
import fr.vertours.safetynetapplication.dto.PersonDTO;
import fr.vertours.safetynetapplication.model.Address;
import fr.vertours.safetynetapplication.model.FireStation;
import fr.vertours.safetynetapplication.model.Person;
import fr.vertours.safetynetapplication.repository.FireStationRepository;
import fr.vertours.safetynetapplication.repository.MedicalRecordRepository;
import fr.vertours.safetynetapplication.repository.PersonRepository;
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

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class PersonLoader {

}
