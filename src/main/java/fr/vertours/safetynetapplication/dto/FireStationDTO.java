package fr.vertours.safetynetapplication.dto;

import fr.vertours.safetynetapplication.model.Address;
import fr.vertours.safetynetapplication.model.FireStation;

import java.util.Set;
import java.util.stream.Collectors;

public class FireStationDTO {

    private Set<String> address;
    private int station;


    public FireStation createFireStation(){
        Set<Address> setAddress = address.stream().map(Address::new).collect(Collectors.toSet());
        FireStation fireStation = new FireStation(setAddress, getStation());
        return fireStation;
    }


    public Set<String> getAddress() {
        return address;
    }
    public void setAddress(Set<String> address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }
    public void setStation(int station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "FireStationDTO{" +
                "address='" + address + '\'' +
                ", station=" + station +
                '}';
    }
}
