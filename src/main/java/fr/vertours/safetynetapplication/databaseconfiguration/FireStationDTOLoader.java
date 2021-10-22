package fr.vertours.safetynetapplication.databaseconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import fr.vertours.safetynetapplication.model.Address;
import fr.vertours.safetynetapplication.model.FireStation;
import fr.vertours.safetynetapplication.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FireStationDTOLoader {

    @Autowired
    FireStationService fireStationService;

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
}










