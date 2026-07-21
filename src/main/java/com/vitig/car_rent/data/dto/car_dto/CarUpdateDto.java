package com.vitig.car_rent.data.dto.car_dto;

import com.vitig.car_rent.data.util.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarUpdateDto {
    private CarStatus status;
    private String plate;
    private Long carId;
}
