package fr.vertours.safetynetapplication.databaseconfiguration;

import fr.vertours.safetynetapplication.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FireStationLoader {

    @Value("classpath:Json/data.json")
    Resource resource;

    @Autowired
    FireStationService fireStationService;
}
