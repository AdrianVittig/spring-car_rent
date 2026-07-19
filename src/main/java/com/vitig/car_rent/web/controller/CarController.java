package com.vitig.car_rent.web.controller;

import com.vitig.car_rent.data.dto.car_dto.CarCreateDto;
import com.vitig.car_rent.data.dto.car_dto.CarFetchDto;
import com.vitig.car_rent.data.dto.car_dto.CarUpdateDto;
import com.vitig.car_rent.service.contract.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public List<CarFetchDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public CarFetchDto getCarById(Long id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public CarFetchDto createCar(CarCreateDto carCreateDto) {
        return carService.createCar(carCreateDto);
    }

    @PostMapping("/{id}")
    public CarFetchDto updateCar(Long id, CarUpdateDto carUpdateDto) {
        return carService.updateCar(id, carUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(Long id) {
        carService.deleteCar(id);
    }

}
