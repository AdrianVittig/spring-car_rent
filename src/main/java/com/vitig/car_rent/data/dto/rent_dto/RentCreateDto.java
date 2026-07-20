package com.vitig.car_rent.data.dto.rent_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentCreateDto {
    private Long carId;
    private Long customerId;
    private Long rentDate;
}
