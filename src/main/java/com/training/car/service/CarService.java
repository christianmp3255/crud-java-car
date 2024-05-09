package com.training.car.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.training.car.repository.CarRepository;
import com.training.car.dto.CarDTO;
import com.training.car.dto.SuccessResponseDTO;
import com.training.car.entity.CarEntity;

// @Slf4j
@Service
public class CarService {
    @Autowired
    private CarRepository repository;

    public List<CarEntity> getAllCars() {
        List<CarEntity> cars = repository.findAll();
        return cars;
    }

    public CarEntity getCarById(Long id) {
        Optional<CarEntity> optCar = repository.findById(id);

        if (optCar.isPresent()) {
            CarEntity car = optCar.get();
            return car;
        } else {
            throw new Error("errinho maroto");
        }
    }

    private CarEntity buildCarEntity(CarDTO carDTO) {
        Date date = new Date();
        CarEntity carEntity = new CarEntity();

        carEntity.setModel(carDTO.getModel());
        carEntity.setColor(carDTO.getColor());
        carEntity.setPower(carDTO.getPower());
        carEntity.setProducer(carDTO.getProducer());
        carEntity.setYearOfManufacture(carDTO.getYearOfManufacture());
        carEntity.setCreateAt(date);
        carEntity.setUpdateAt(date);

        return carEntity;
    }

    public SuccessResponseDTO saveNewCar(CarDTO carDTO) {
        CarEntity carEntity = buildCarEntity(carDTO);
        CarEntity savedCar = repository.saveAndFlush(carEntity);

        SuccessResponseDTO successResponseDTO = new SuccessResponseDTO();
        successResponseDTO.setCode("201");
        successResponseDTO.setStatus("CREATED");
        successResponseDTO.setMessage("Carro [" + savedCar.getModel() + "] salvo com sucesso!");

        return successResponseDTO;
    }

    private void updateOldCar (CarDTO newCar, CarEntity oldCar) {
        if (newCar.getModel() != null && !oldCar.getModel().isEmpty()) {
            oldCar.setModel(newCar.getModel());
        }
        if (newCar.getColor() != null && !oldCar.getColor().isEmpty()) {
            oldCar.setColor(newCar.getColor());
        }
        if (newCar.getProducer() != null && !oldCar.getProducer().isEmpty()) {
            oldCar.setProducer(newCar.getProducer());
        }
        oldCar.setYearOfManufacture(newCar.getYearOfManufacture());
        oldCar.setPower(newCar.getPower());
        oldCar.setUpdateAt(new Date());
    }

    // Como aqui nos parametros, estamos esperando o updateCar do tipo CarDTO, não deveria ser obrigado ser preenchido todos os campos q existem no CarDTO, ou seja, não deveria ser disparado uma exception caso não fosse preenchido TODOS os campos?
    public SuccessResponseDTO updateCarById (Long id, CarDTO updateCar) {
        Optional<CarEntity> optCar = repository.findById(id);

        CarEntity oldCar = null;

        if (optCar.isPresent()) {
            oldCar = optCar.get();
        } else {
            throw new Error ("Não deu pra achar algo com esse fucking id: " + id);
        }

        // A variavel oldCad quando vai para a função updateOldCar e é modificada, a modificação é feita nessa mesma instancia?
        updateOldCar(updateCar, oldCar);
        repository.save(oldCar);

        SuccessResponseDTO successResponse = new SuccessResponseDTO();
        successResponse.setCode("200");
        successResponse.setStatus("UPDATED");
        successResponse.setMessage("Feito soares! Mudei o carro com id: " + oldCar.getId());
        return successResponse;
    }

    public SuccessResponseDTO deleteCarById (Long id) {
        boolean isCarId = repository.existsById(id);

        if (isCarId) {
            repository.deleteById(id);
            SuccessResponseDTO successResponse = new SuccessResponseDTO();
            successResponse.setCode("200");
            successResponse.setStatus("UPDATED");
            successResponse.setMessage("Feito soares! Deletei o carro com id: " + id);
            return successResponse;
        } else {
            throw new Error ("Não deu pra achar algo com esse fucking id: " + id);
        }
    }
}
