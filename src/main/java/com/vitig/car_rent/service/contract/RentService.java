package com.vitig.car_rent.service.contract;

import com.vitig.car_rent.data.dto.rent_dto.RentCreateDto;
import com.vitig.car_rent.data.dto.rent_dto.RentFetchDto;
import com.vitig.car_rent.data.dto.rent_dto.RentUpdateDto;
import com.vitig.car_rent.data.entity.Rent;

import java.time.LocalDateTime;
import java.util.List;

public interface RentService {
    List<RentFetchDto> getAllRents();
    RentFetchDto getRentById(Long id);
    RentFetchDto createRent(RentCreateDto rentCreateDto);
    RentFetchDto updateRent(Long id, RentUpdateDto rentUpdateDto);
    void deleteRent(Long id);
    List<Rent> findOverlappingRents(Long carId, LocalDateTime startDate, LocalDateTime endDate);
}
