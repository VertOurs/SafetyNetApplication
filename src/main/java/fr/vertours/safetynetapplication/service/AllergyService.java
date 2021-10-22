package fr.vertours.safetynetapplication.service;

import fr.vertours.safetynetapplication.model.Allergy;
import fr.vertours.safetynetapplication.repository.AllergyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllergyService {

    @Autowired
    private AllergyRepository allergyRepository;

    public Allergy find(String allergyName) {
        return allergyRepository.findOneByAllergy(allergyName);
    }

    public Allergy save(String allergyName) {
        Allergy allergy = new Allergy();
        allergy.setAllergy(allergyName);
        return save(allergy);
    }

    public Allergy save( Allergy allergy) {
        return allergyRepository.save(allergy);
    }

    public Allergy findOrCreate(String allergyName) {
        Allergy allergy = find(allergyName);
        if (allergy == null) {
            allergy = save(allergyName);
        }
        return allergy;
    }


}
