package com.vitig.car_rent.service.impl;

import com.vitig.car_rent.config.util.ModelMapperUtil;
import com.vitig.car_rent.data.dto.car_dto.CarFetchDto;
import com.vitig.car_rent.data.dto.customer_dto.CustomerCreateDto;
import com.vitig.car_rent.data.dto.customer_dto.CustomerFetchDto;
import com.vitig.car_rent.data.dto.customer_dto.CustomerUpdateDto;
import com.vitig.car_rent.data.entity.Car;
import com.vitig.car_rent.data.entity.Customer;
import com.vitig.car_rent.data.exception.ObjectNotFoundException;
import com.vitig.car_rent.data.exception.OperationNotAvailableException;
import com.vitig.car_rent.data.repository.CustomerRepository;
import com.vitig.car_rent.service.contract.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Override
    public List<CustomerFetchDto> getAllCustomers() {
        return this.customerRepository.findAll().stream()
                .map(customer -> modelMapperUtil.map(customer, CustomerFetchDto.class))
                .toList();
    }

    @Override
    public CustomerFetchDto getCustomerById(Long id) {
        return modelMapperUtil.map(this.customerRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("User not found with id " + id + "!")
        ), CustomerFetchDto.class);
    }

    @Override
    public Customer getCustomerEntityById(Long id) {
        return this.customerRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("User not found with id " + id + "!")
        );
    }

    @Override
    @Transactional
    public CustomerFetchDto createCustomer(CustomerCreateDto customerCreateDto) {
        Customer customer = modelMapperUtil.map(customerCreateDto, Customer.class);
        return modelMapperUtil.map(this.customerRepository.save(customer), CustomerFetchDto.class);
    }

    @Override
    @Transactional
    public CustomerFetchDto updateCustomer(Long id, CustomerUpdateDto customerUpdateDto) {
        throw new OperationNotAvailableException("You can't update a customer!");
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("User not found with id " + id + "!")
        );
        this.customerRepository.delete(customer);
    }
}
