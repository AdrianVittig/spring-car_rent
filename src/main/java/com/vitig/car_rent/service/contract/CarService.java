package com.vitig.car_rent.service.contract;

import com.vitig.car_rent.data.dto.car_dto.CarCreateDto;
import com.vitig.car_rent.data.dto.car_dto.CarFetchDto;
import com.vitig.car_rent.data.dto.car_dto.CarUpdateDto;

import java.util.List;

public interface CarService {
    List<CarFetchDto> getAllCars();
    CarFetchDto getCarById(Long id);
    CarFetchDto createCar(CarCreateDto carCreateDto);
    CarFetchDto updateCar(Long id, CarUpdateDto carUpdateDto);
    void deleteCar(Long id);
}
