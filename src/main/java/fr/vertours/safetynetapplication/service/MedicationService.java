package fr.vertours.safetynetapplication.service;

import fr.vertours.safetynetapplication.model.Medication;
import fr.vertours.safetynetapplication.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {

    @Autowired
    MedicationRepository medicationRepository;


    public Medication find(String medicationName){
        return medicationRepository.findOneByMedication(medicationName);
    }
    public Medication save(String medicationName) {
        Medication medication = new Medication();
        medication.setMedication(medicationName);
        return save(medication);
    }
    public Medication save( Medication medication) {
        return medicationRepository.save(medication);
    }
    public Medication findOrCreate(String medicationName) {
        Medication medication = find(medicationName);
        if (medication == null) {
            medication = save(medicationName);
        }
        return medication;
    }
}
