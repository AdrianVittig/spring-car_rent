package com.vitig.car_rent.service.impl;

import com.vitig.car_rent.config.util.ModelMapperUtil;
import com.vitig.car_rent.data.dto.car_dto.CarCreateDto;
import com.vitig.car_rent.data.dto.car_dto.CarFetchDto;
import com.vitig.car_rent.data.dto.car_dto.CarUpdateDto;
import com.vitig.car_rent.data.entity.Car;
import com.vitig.car_rent.data.exception.ObjectNotFoundException;
import com.vitig.car_rent.data.repository.CarRepository;
import com.vitig.car_rent.service.contract.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Override
    public List<CarFetchDto> getAllCars() {
        return this.carRepository.findAll().stream()
                .map(brand -> modelMapperUtil.map(brand, CarFetchDto.class))
                .toList();
    }

    @Override
    public CarFetchDto getCarById(Long id) {
        return modelMapperUtil.map(this.carRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Car not found with id: " + id + "!")
        ), CarFetchDto.class);
    }

    @Override
    public Car getCarEntityById(Long id) {
        return this.carRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Car not found with id: " + id + "!")
        );
    }

    @Override
    @Transactional
    public CarFetchDto createCar(CarCreateDto carCreateDto) {
        Car car = modelMapperUtil.map(carCreateDto, Car.class);
        return modelMapperUtil.map(this.carRepository.save(car), CarFetchDto.class);
    }

    @Override
    @Transactional
    public CarFetchDto updateCar(Long id, CarUpdateDto carUpdateDto) {
        Car car = this.carRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Car not found with id: " + id + "!")
        );
        car.setPlate(carUpdateDto.getPlate());
        car.setStatus(carUpdateDto.getStatus());
        return modelMapperUtil.map(this.carRepository.save(car), CarFetchDto.class);
    }

    @Override
    @Transactional
    public void deleteCar(Long id) {
        Car car = this.carRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Car not found with id: " + id + "!")
        );
        this.carRepository.delete(car);
    }

}
