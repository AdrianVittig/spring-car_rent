package com.vitig.car_rent.service.impl;

import com.vitig.car_rent.config.util.ModelMapperUtil;
import com.vitig.car_rent.data.dto.car_dto.CarCreateDto;
import com.vitig.car_rent.data.dto.car_dto.CarFetchDto;
import com.vitig.car_rent.data.dto.car_dto.CarUpdateDto;
import com.vitig.car_rent.data.dto.rent_dto.RentCreateDto;
import com.vitig.car_rent.data.entity.Car;
import com.vitig.car_rent.data.exception.CarAlreadyAvailableException;
import com.vitig.car_rent.data.exception.CarAlreadyRentedException;
import com.vitig.car_rent.data.exception.ObjectNotFoundException;
import com.vitig.car_rent.data.repository.CarRepository;
import com.vitig.car_rent.data.util.CarStatus;
import com.vitig.car_rent.service.contract.CarService;
import com.vitig.car_rent.service.contract.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final RentService rentService;
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

    @Override
    public CarFetchDto reserveCar(Long carId, RentCreateDto rentCreateDto) {
        Car car = this.carRepository.findById(carId).orElseThrow(
                () -> new ObjectNotFoundException("Car not found with id: " + carId + "!")
        );

        if(car.getStatus() == CarStatus.RENTED){
            throw new CarAlreadyRentedException("Car already rented!");
        }

        this.rentService.createRent(rentCreateDto);
        car.setStatus(CarStatus.RENTED);

        return modelMapperUtil.map(this.carRepository.save(car), CarFetchDto.class);
    }

    @Override
    public void returnCar(Long carId) {
        Car car = this.carRepository.findById(carId).orElseThrow(
                () -> new ObjectNotFoundException("Car not found with id: " + carId + "!")
        );
        if(car.getStatus() == CarStatus.AVAILABLE){
            throw new CarAlreadyAvailableException("Car already available!");
        }

        car.setStatus(CarStatus.AVAILABLE);
        this.carRepository.save(car);
    }
}
