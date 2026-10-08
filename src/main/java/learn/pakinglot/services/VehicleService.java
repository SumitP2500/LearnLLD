package learn.pakinglot.services;

import learn.pakinglot.models.Vehicle;
import learn.pakinglot.models.VehicleType;
import learn.pakinglot.repositories.InMemoryRepository;

public class VehicleService {
    InMemoryRepository<Vehicle> vehicleRepository;
    
    public VehicleService(InMemoryRepository<Vehicle> vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle getOrCreateVehicle(Long registrationNumber, VehicleType vehicleType,    String ownerName,
    String ownerPhone) {

        Vehicle vehicle = getVehicleByRegistrationNumber(registrationNumber);
        if(vehicle == null) {
            vehicle = new Vehicle();
            vehicle.setRegistrationNumber(registrationNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setOwnerName(ownerName);
            vehicle.setOwnerPhone(ownerPhone);
            vehicleRepository.save(vehicle);
        }

        return vehicle;
    }

    public Vehicle getVehicleByRegistrationNumber(Long registrationNumber) {
        return vehicleRepository.findAll().stream().filter(v -> v.getRegistrationNumber()== registrationNumber).findFirst().orElse(null);
    }
}
