package com.vitig.car_rent.data.dto.rent_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentUpdateDto {
    private Long carId;
    private Long customerId;
    private Long rentDate;
    private Long returnDate;
    private BigDecimal totalPrice;
}
