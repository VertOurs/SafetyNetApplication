package fr.vertours.safetynetapplication.controller;

import fr.vertours.safetynetapplication.model.FireStation;
import fr.vertours.safetynetapplication.service.FireStationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/firestation")
public class FireStationController {

    private final FireStationService firestationService;


    public FireStationController(FireStationService fireStationService) {
        this.firestationService = fireStationService;
    }


    @PostMapping
    public void create(@RequestBody FireStation fireStation) {
        this.firestationService.save(fireStation);
    }

    @GetMapping
    public List<FireStation> getListFireStation(){
        return this.firestationService.findAll();
    }

    @GetMapping ("/{station}")
    public FireStation getStation(@PathVariable int station){
        return this.firestationService.findByStation(station);
    }

}
