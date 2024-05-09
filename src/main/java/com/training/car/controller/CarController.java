package com.training.car.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.car.dto.CarDTO;
import com.training.car.dto.SuccessResponseDTO;
import com.training.car.entity.CarEntity;
import com.training.car.service.CarService;


@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/car")
    public ResponseEntity<SuccessResponseDTO> saveNewCar(@RequestBody CarDTO car) {
        SuccessResponseDTO carResult = carService.saveNewCar(car);
        return ResponseEntity.status(201).body(carResult);
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarEntity>> getAllCars() {
        List<CarEntity> carResult = carService.getAllCars();
        return ResponseEntity.status(200).body(carResult);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarEntity> getCarById(@PathVariable("id") Long id) {
        CarEntity carResult = carService.getCarById(id);
        return ResponseEntity.status(200).body(carResult);
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<SuccessResponseDTO> updateCarById(@PathVariable Long id, @RequestBody CarDTO updateCar) {
        SuccessResponseDTO successUpdateCar = carService.updateCarById(id, updateCar);
        return ResponseEntity.status(200).body(successUpdateCar);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<SuccessResponseDTO> deleteCarById(@PathVariable Long id) {
        SuccessResponseDTO successResponse = carService.deleteCarById(id);
        return ResponseEntity.status(200).body(successResponse);
    }
}
