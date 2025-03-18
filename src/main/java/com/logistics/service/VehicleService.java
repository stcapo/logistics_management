package com.logistics.service;

import com.logistics.entity.Vehicle;
import com.logistics.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vehicleRepository;
    
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }
    
    public Vehicle findById(Integer id) {
        return vehicleRepository.findById(id);
    }
    
    public boolean insert(Vehicle vehicle) {
        return vehicleRepository.insert(vehicle) > 0;
    }
    
    public boolean update(Vehicle vehicle) {
        return vehicleRepository.update(vehicle) > 0;
    }
    
    public boolean deleteById(Integer id) {
        return vehicleRepository.deleteById(id) > 0;
    }
}
