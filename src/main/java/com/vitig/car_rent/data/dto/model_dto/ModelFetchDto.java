package com.vitig.car_rent.data.dto.model_dto;

import com.vitig.car_rent.data.dto.brand_dto.BrandFetchDto;
import com.vitig.car_rent.data.util.CarColor;
import com.vitig.car_rent.data.util.CarFuel;
import com.vitig.car_rent.data.util.CarModel;
import com.vitig.car_rent.data.util.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelFetchDto {
    private Long id;
    private CarModel model;
    private Integer year;
    private CarColor color;
    private CarType type;
    private Integer seats;
    private Integer doors;
    private CarFuel fuel;
    private Integer minimalAge;
    private Long brandId;
    private BrandFetchDto brand;
}
