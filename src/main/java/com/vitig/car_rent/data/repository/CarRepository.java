package com.vitig.car_rent.data.repository;

import com.vitig.car_rent.data.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
