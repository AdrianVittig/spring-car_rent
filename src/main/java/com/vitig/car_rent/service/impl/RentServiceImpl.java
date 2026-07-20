package com.vitig.car_rent.service.impl;

import com.vitig.car_rent.config.util.ModelMapperUtil;
import com.vitig.car_rent.data.dto.rent_dto.RentCreateDto;
import com.vitig.car_rent.data.dto.rent_dto.RentFetchDto;
import com.vitig.car_rent.data.dto.rent_dto.RentUpdateDto;
import com.vitig.car_rent.data.entity.Customer;
import com.vitig.car_rent.data.entity.Rent;
import com.vitig.car_rent.data.exception.ObjectNotFoundException;
import com.vitig.car_rent.data.repository.RentRepository;
import com.vitig.car_rent.service.contract.CustomerService;
import com.vitig.car_rent.service.contract.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {
    private final RentRepository rentRepository;
    private final CustomerService customerService;
    private final ModelMapperUtil modelMapperUtil;

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
        Rent rent = modelMapperUtil.map(rentCreateDto, Rent.class);
        return modelMapperUtil.map(this.rentRepository.save(rent), RentFetchDto.class);
    }

    @Override
    @Transactional
    public RentFetchDto updateRent(Long id, RentUpdateDto rentUpdateDto) {
        Rent rent = this.rentRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Rent not found with id: " + id + "!")
        );
        Customer customer = modelMapperUtil.map(customerService.getCustomerById(rentUpdateDto.getCustomerId()), Customer.class);
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
}
