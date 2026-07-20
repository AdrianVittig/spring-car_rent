package com.vitig.car_rent.data.dto.car_dto;

import com.vitig.car_rent.data.dto.model_dto.ModelFetchDto;
import com.vitig.car_rent.data.util.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarFetchDto {
    private Long id;
    private String plate;
    private CarStatus status;
    private Long customerId;
    private Long modelId;
    private ModelFetchDto model;
}
