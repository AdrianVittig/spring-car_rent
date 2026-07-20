package com.vitig.car_rent.data.repository;

import com.vitig.car_rent.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
