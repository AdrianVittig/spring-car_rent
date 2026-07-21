package com.vitig.car_rent.service.impl;

import com.vitig.car_rent.config.util.ModelMapperUtil;
import com.vitig.car_rent.data.dto.car_dto.CarFetchDto;
import com.vitig.car_rent.data.dto.rent_dto.RentCreateDto;
import com.vitig.car_rent.data.dto.rent_dto.RentFetchDto;
import com.vitig.car_rent.data.dto.rent_dto.RentUpdateDto;
import com.vitig.car_rent.data.entity.Car;
import com.vitig.car_rent.data.entity.Customer;
import com.vitig.car_rent.data.entity.Rent;
import com.vitig.car_rent.data.exception.CarAlreadyRentedException;
import com.vitig.car_rent.data.exception.ObjectNotFoundException;
import com.vitig.car_rent.data.repository.RentRepository;
import com.vitig.car_rent.data.util.CarStatus;
import com.vitig.car_rent.service.contract.CarService;
import com.vitig.car_rent.service.contract.CustomerService;
import com.vitig.car_rent.service.contract.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {
    private final RentRepository rentRepository;
    private final CustomerService customerService;
    private final ModelMapperUtil modelMapperUtil;
    private final CarService carService;

    @Override
    public List<RentFetchDto> getAllRents() {
        return this.rentRepository.findAll().stream()
                .map(model -> modelMapperUtil.map(model, RentFetchDto.class))
                .toList();
    }

    @Override
    public RentFetchDto getRentById(Long id) {
        return modelMapperUtil.map(this.rentRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Rent not found with id " + id + "!")
        ), RentFetchDto.class);
    }

    @Override
    @Transactional
    public RentFetchDto createRent(RentCreateDto rentCreateDto) {
        Car car = carService.getCarEntityById(rentCreateDto.getCarId());
        Customer customer = customerService.getCustomerEntityById(rentCreateDto.getCustomerId());

        Long daysCount = ChronoUnit.DAYS.between(rentCreateDto.getRentDate(),
                rentCreateDto.getReturnDate());

        if(rentCreateDto.getRentDate().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Rent date must be greater than current date!");
        }

        if(rentCreateDto.getReturnDate().isBefore(rentCreateDto.getRentDate())){
            throw new IllegalArgumentException("Return date must be greater than rent date!");
        }

        List<Rent> rents = getByRentIdAndRentDateBetweenAndReturnDate(car.getId(),
                rentCreateDto.getRentDate(),
                rentCreateDto.getReturnDate());

        if(!rents.isEmpty()){
            throw new CarAlreadyRentedException("Car already rented!");
        }

        BigDecimal total = car.getModel().getPricePerDay().multiply(new BigDecimal(daysCount));

        Rent rent = new Rent();
        rent.setRentDate(rentCreateDto.getRentDate());
        rent.setReturnDate(rentCreateDto.getReturnDate());
        rent.setCar(car);
        rent.setCustomer(customer);
        rent.setTotalPrice(total);
        car.setStatus(CarStatus.RENTED);

        return modelMapperUtil.map(this.rentRepository.save(rent), RentFetchDto.class);
    }

    @Override
    @Transactional
    public RentFetchDto updateRent(Long id, RentUpdateDto rentUpdateDto) {
        Rent rent = this.rentRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Rent not found with id: " + id + "!")
        );
        Customer customer = customerService.getCustomerEntityById(rentUpdateDto.getCustomerId());
        rent.setRentDate(rentUpdateDto.getRentDate());
        rent.setReturnDate(rentUpdateDto.getReturnDate());
        rent.setTotalPrice(rentUpdateDto.getTotalPrice());
        rent.setCustomer(customer);
        return modelMapperUtil.map(this.rentRepository.save(rent), RentFetchDto.class);
    }

    @Override
    @Transactional
    public void deleteRent(Long id) {
        Rent rent = this.rentRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Rent not found with id: " + id + "!")
        );
        this.rentRepository.delete(rent);
    }

    @Override
    public List<Rent> getByRentIdAndRentDateBetweenAndReturnDate(Long carId ,LocalDateTime startDate, LocalDateTime endDate) {
        return this.rentRepository.findOverlappingRents(carId, startDate, endDate);
    }
}
