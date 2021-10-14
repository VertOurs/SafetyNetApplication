package fr.vertours.safetynetapplication.service;

import fr.vertours.safetynetapplication.model.FireStation;
import fr.vertours.safetynetapplication.repository.FireStationRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class FireStationService {

    private final FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }


    public void save(FireStation fireStation) {
        fireStationRepository.save(fireStation);
    }
    public List<FireStation> findAll(){
        return fireStationRepository.findAll();
    }
    public FireStation findByStation(int fireStation) {
        return fireStationRepository.findByStation(fireStation);
    }
    public void saveAll(Collection<FireStation> fireStationCollection){
        fireStationRepository.saveAll(fireStationCollection);
    }
}
