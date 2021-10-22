package fr.vertours.safetynetapplication.service;

import fr.vertours.safetynetapplication.dto.FireStationDTO;
import fr.vertours.safetynetapplication.model.Address;
import fr.vertours.safetynetapplication.model.FireStation;
import fr.vertours.safetynetapplication.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    private final FireStationRepository fireStationRepository;

    @Autowired
    AddressService addressService;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }


    public void saveOneStation(FireStationDTO fireStationDTO) {
        Set<Address> addressSet = fireStationDTO.getAddress().stream().map(address -> addressService.findOrCreate(address)).collect(Collectors.toSet());
        FireStation fireStation = fireStationDTO.createFireStation();
        fireStation.setAddress(addressSet);
        fireStationRepository.save(fireStation);
    }
    public void saveAllStations(Collection<FireStation> fireStationCollection){
        fireStationRepository.saveAll(fireStationCollection);
    }

    public List<FireStation> getListOfAllStations(){
        return fireStationRepository.findAll();
    }

    public FireStation findOneStation(int fireStation) {
        return fireStationRepository.findByStation(fireStation);
    }

    public void deleteOneFireStation(int firestation) {
        FireStation fireStationObject = fireStationRepository.findByStation(firestation);
        fireStationRepository.delete(fireStationObject);
    }

    public void updateStationForOneAddress(int station, Address address) {
        FireStation fireStation = fireStationRepository.findByStation(station);
        Address addressObject = addressService.findOrCreate(address.getAddressName());
        fireStation.addAdress(addressObject);
        fireStationRepository.save(fireStation);


    }









}