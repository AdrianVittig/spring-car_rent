package com.vitig.car_rent.data.repository;

import com.vitig.car_rent.data.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    @Query("SELECT r FROM Rent r WHERE r.car.id = :carId AND :startDate <= r.returnDate AND :endDate >= r.rentDate")
    List<Rent> findOverlappingRents(@Param("carId") Long carId,
                                    @Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);

    List<Rent> findByCustomerId(Long customerId);
}
