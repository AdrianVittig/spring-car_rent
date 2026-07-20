package com.vitig.car_rent.data.repository;

import com.vitig.car_rent.data.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
