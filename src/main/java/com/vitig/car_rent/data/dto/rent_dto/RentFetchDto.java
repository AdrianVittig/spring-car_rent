package com.vitig.car_rent.data.dto.rent_dto;

import com.vitig.car_rent.data.dto.car_dto.CarFetchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentFetchDto {
    private Long id;
    private Long carId;
    private CarFetchDto car;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;
    private BigDecimal totalPrice;
}
