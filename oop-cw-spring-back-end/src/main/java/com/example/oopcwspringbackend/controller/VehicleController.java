package com.example.oopcwspringbackend.controller;

import com.example.oopcwspringbackend.model.*;
import com.example.oopcwspringbackend.repository.CarRepository;
import com.example.oopcwspringbackend.repository.MotorbikeRepository;
import com.example.oopcwspringbackend.repository.VanRepository;
import com.example.oopcwspringbackend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private VanRepository vanRepository;

    @Autowired
    private MotorbikeRepository motorbikeRepository;
    // the method used to return all the Vehicles in the collection
    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles(@RequestParam(value = "sort", defaultValue = "make") String sort, @RequestParam(value = "vehicletype", defaultValue = "All") String vehicleType) {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        List<Vehicle> resultList = new ArrayList<>();
        System.out.println(vehicleList);
        //if the filter method parameter hasn't been passed then it will not be filtered all the vehicles will be sent
        // if it has been passed then the filter will happen according to the value passed for type
        if (vehicleType.equalsIgnoreCase("Car") || vehicleType.equalsIgnoreCase("Van") || vehicleType.equalsIgnoreCase("Motorbike")) {
            for (Vehicle vehicle : vehicleList) {
                String type = vehicle.get_class();
                if (type.substring(37).equalsIgnoreCase(vehicleType)) {
                    resultList.add(vehicle);
                }
            }
        } else {
            resultList = vehicleList;
        }
        //if the sort method parameter hasn't been passed then it will be sorted
        // according to the make and if it has been passed then the sort will happen according to the value passed
        if (sort.equalsIgnoreCase("price-")) {
            resultList.sort(new SortByPrice().reversed());
        } else if (sort.equalsIgnoreCase("price+")) {
            resultList.sort(new SortByPrice());
        } else {
            Collections.sort(resultList);
        }
        //the list is then sent
        return resultList;
    }


}
