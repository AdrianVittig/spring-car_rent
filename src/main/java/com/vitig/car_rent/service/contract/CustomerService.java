package com.vitig.car_rent.service.contract;

import com.vitig.car_rent.data.dto.customer_dto.CustomerCreateDto;
import com.vitig.car_rent.data.dto.customer_dto.CustomerFetchDto;
import com.vitig.car_rent.data.dto.customer_dto.CustomerUpdateDto;
import com.vitig.car_rent.data.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerFetchDto> getAllCustomers();
    CustomerFetchDto getCustomerById(Long id);
    Customer getCustomerEntityById(Long id);
    CustomerFetchDto createCustomer(CustomerCreateDto customerCreateDto);
    CustomerFetchDto updateCustomer(Long id, CustomerUpdateDto customerUpdateDto);
    void deleteCustomer(Long id);
}
