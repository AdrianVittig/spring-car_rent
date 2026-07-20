package com.vitig.car_rent.service.contract;

import com.vitig.car_rent.data.dto.rent_dto.RentCreateDto;
import com.vitig.car_rent.data.dto.rent_dto.RentFetchDto;
import com.vitig.car_rent.data.dto.rent_dto.RentUpdateDto;

import java.util.List;

public interface RentService {
    List<RentFetchDto> getAllRents();
    RentFetchDto getRentById(Long id);
    RentFetchDto createRent(RentCreateDto rentCreateDto);
    RentFetchDto updateRent(Long id, RentUpdateDto rentUpdateDto);
    void deleteRent(Long id);
}
